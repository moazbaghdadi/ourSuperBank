package fw.springboot.bank.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import fw.springboot.bank.Transaction;

@Component
public class BankJMSConsumer {

	@Autowired
	BankJMSTransactionHandler bankJMSTransactionHandler;

	private BankJMSConsumer() {
	}

	@JmsListener(destination = "bank_s")
	public void receiveMessage(Transaction receiveTransaction) {
		System.out.println("Received <" + receiveTransaction + ">");
		bankJMSTransactionHandler.proceedTransaction(receiveTransaction);
	}

}
