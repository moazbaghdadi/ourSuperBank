package fw.springboot.bank;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

	/**
	 * creates a new BankAccount
	 * @return BankAccount new instance with an Account number
	 */
	public BankAccount createAccount();

	/**
	 * Queries BankRepository to retrieve all BankAccounts of this Bank
	 * @return list of all BankAccounts in the Database of Bank
	 */
	public List<BankAccount> getAllAccounts();

	/**
	 * queries BankRepository through accountNumber to get the BankAccount instance 
	 * @param accountNumber number of the BankAccount
	 * @return an instance of BankAccount except accountNumber is invalid, then null
	 */
	public BankAccount getOneAccount(String accountNumber);

	/**
	 * queries BankRepository through accountNumber to get the BankAccount instance and then retrieves the Balance from the Account
	 * @param accountNumber number of the BankAccount
	 * @return a BigDecimal instance with the value of the balance from the BankAccount
	 */
	public BigDecimal getBalance(String accountNumber);

	/**
	 * queries BankRepository through accountNumber to getthe BankAccount instance and then adds the amount to the balance of the Account and updates the Database
	 * @param accountNumber number of the BankAccount
	 * @param amount a BigDecimal instance with the value of the book Amount
	 * @return a BigDecimal instance with the value of the new Balance of the BankAccount
	 */
	public BigDecimal book(String accountNumber, BigDecimal amount);

}
