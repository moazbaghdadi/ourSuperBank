package fw.springboot.bank;

import java.math.BigDecimal;

public class Transaction {
	private String fromAccount;
	private String toAccount;
	private BigDecimal amount;
	private Status status;

	public enum Status {
		FINISHED, IN_PROCESS, FAILED
	}

	private Transaction() {
	}

	public Transaction(String fromAccount, String toAccount, BigDecimal amount) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.setStatus(Status.IN_PROCESS);
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Status getStatus() {
		return status;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + this.getClass().getSimpleName() + "] fromAccount: " + fromAccount + " toAccount: " + toAccount
				+ " amount: " + amount + " status: " + status;
	}

}
