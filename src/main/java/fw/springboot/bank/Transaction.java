package fw.springboot.bank;

import java.math.BigDecimal;

public class Transaction {
	
	private String fromAccount;
	
	private String toAccount;
	
	private BigDecimal amount;
	
	private Status status;
	
	public enum Status{
		FINISHED, IN_PROCESS, FAILED
	}


	public Transaction(String from, String to, BigDecimal amount) {
	
		this.fromAccount = from;
		this.toAccount = to;
		this.amount = amount;
		this.setStatus(Status.IN_PROCESS);
	}
	

	
	public String getFrom() {
		return fromAccount;
	}



	public String getTo() {
		return toAccount;
	}



	public BigDecimal getAmount() {
		return amount;
	}





	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	

}
