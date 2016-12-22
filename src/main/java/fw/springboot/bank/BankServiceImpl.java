package fw.springboot.bank;

import java.math.BigDecimal;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import com.hazelcast.client.impl.protocol.codec.TransactionalMapMessageType;

import fw.springboot.bank.exception.BankServiceException;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankAccountRepository accountRepository;

	@Autowired
	private NumberGenerator numberGenerator;

	public NumberGenerator getNumberGenerator() {
		return numberGenerator;
	}

	public void setNumberGenerator(NumberGenerator numberGenerator) {
		this.numberGenerator = numberGenerator;
	}

	@Override
	public BankAccount createAccount() {
		BankAccount bankAccount = new BankAccount(numberGenerator.getNextNumber());
		accountRepository.save(bankAccount);
		return bankAccount;
	}

	@Override
	public List<BankAccount> getAllAccounts() {
		return (List<BankAccount>) accountRepository.findAll();
	}

	@Override
	public BankAccount getOneAccount(String accountNumber) {
		// [krausg] parameter type check wird durch aop gemacht, meint [fabio]
		BankAccount bankAccount = accountRepository.findBankAccountByAccountNumber(accountNumber);
		if (bankAccount != null) {
			return bankAccount;
		} else {
			throw new BankServiceException("Account number not found in Bank");
		}
	}

	@Override
	public BigDecimal getBalance(String accountNumber) {
		return getOneAccount(accountNumber).getBalance();
	}

	@Override
	public Transaction book(Transaction tx)  {
		
		if(tx.getFrom() == null && tx.getTo() == null){
			tx.setStatus(Transaction.Status.FAILED);
			return tx;
		}
		
		try{
			BankAccount bankAccount;

			if(tx.getFrom() != null){
				//deposit
				bankAccount = getOneAccount(tx.getFrom());
				bankAccount.setBalance(bankAccount.getBalance().subtract(tx.getAmount()));
			}else {
				//withdrawal
				bankAccount = getOneAccount(tx.getTo());
				bankAccount.setBalance(bankAccount.getBalance().add(tx.getAmount()));
			}

			accountRepository.save(bankAccount);			
			tx.setStatus(Transaction.Status.FINISHED);
		}catch (Exception e) {
			tx.setStatus(Transaction.Status.FAILED);
		}

		return tx;
	}

	/**
	 * Checks if a BigDecimal is greater than 0
	 * @param amount bigdecimal instance
	 * @return boolean will be true, if it is greater or equals than 0 false 
	 */
	private boolean isGreaterEqualsThanZero(BigDecimal amount) {
		return amount.compareTo(BigDecimal.ZERO) >= 0;
	}

	public BankAccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(BankAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
