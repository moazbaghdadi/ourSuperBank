package fw.springboot.bank;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return accountRepository.findBankAccountByAccountNumber(accountNumber);
	}

	@Override
	public BigDecimal getBalance(String accountNumber) {
		BankAccount bankAccount = accountRepository.findBankAccountByAccountNumber(accountNumber);
		if (bankAccount != null) {
			return bankAccount.getBalance();
		} else {
			throw new BankServiceException("Account number not found in Bank");
		}
	}

	@Override
	public BigDecimal book(String accountNumber, BigDecimal amount) {
		// [krausg] wird ersetzt durch aop, meint [fabio]
		// if (accountNumber == null || accountNumber.length() <= 0) {
		// throw new BankServiceException("AccountNumber is NOT allowed
		// to be null or empty");
		// }

		BankAccount bankAccount = accountRepository.findBankAccountByAccountNumber(accountNumber);
		if (isGreaterEqualsThanZero(amount)) {
			bankAccount.setBalance(bankAccount.getBalance().add(amount));
		} else {
			bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
		}

		accountRepository.save(bankAccount);
		return bankAccount.getBalance();
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
