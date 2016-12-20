package fw.springboot.bank;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String accountNumber;
	private BigDecimal balance;

	public BankAccount() {
		// for spring
	}

	public BankAccount(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object comparedObj) {
		if (comparedObj == null || !(comparedObj instanceof BankAccount)) {
			return false;
		}

		if (!equalsTo((BankAccount) comparedObj)) {
			return false;
		}

		return super.equals(comparedObj);
	}

	/**
	 * Compares instance of BankAccount withAnother BankAccount
	 * @param anotherBankAccount
	 * @return
	 */
	private boolean equalsTo(BankAccount anotherBankAccount) {
		// is Id equals to comparedAccount
		if (this.getId().equals(anotherBankAccount.getId())) {
			return false;
		}

		// is balance equals to comparedAccount
		if (this.getBalance().equals(anotherBankAccount.getBalance())) {
			return false;
		}

		// is accountNumber equals to comparedAccount
		if (this.getAccountNumber().equals(anotherBankAccount.getAccountNumber())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * (result + generateHashCodeIfNotNull(this.getId()));
		result = prime * (result + generateHashCodeIfNotNull(this.getAccountNumber()));
		result = prime * (result + generateHashCodeIfNotNull(this.getBalance()));
		return result;
	}

	/**
	 * generates HashCode if object is not null
	 * @param objectWithHashCode
	 * @return hashcode of objectWithHashCode or 0, if null
	 */
	private int generateHashCodeIfNotNull(Object objectWithHashCode) {
		return (objectWithHashCode == null) ? 0 : objectWithHashCode.hashCode();
	}

}
