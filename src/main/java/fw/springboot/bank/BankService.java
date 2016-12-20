package fw.springboot.bank;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

	public BankAccount createAccount();
	
	public List<BankAccount> getAllAccounts();
	
	public BankAccount getOneAccount(String accountNumber);
	
	public BigDecimal getBalance(String accountNumber);
	
	public BigDecimal book(String accountNumber, BigDecimal amount);
	
}
