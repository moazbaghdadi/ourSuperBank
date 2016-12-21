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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getOneAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBalance(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal book(String accountNumber, BigDecimal amount) {
		// accountRepository.findBankAccountByAccountNumber(accountNumber)
		return null;
	}

	public BankAccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(BankAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
