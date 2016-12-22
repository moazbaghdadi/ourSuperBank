package fw.springboot.bank.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fw.springboot.bank.BankService;
import fw.springboot.bank.Transaction;
import fw.springboot.bank.Transaction.Status;

@Component
public class BankJMSTransactionHandler {

	@Autowired
	BankService bankService;

	@Autowired
	BankJMSProducer bankJMSProducer;

	public BankJMSTransactionHandler() {
	}

	public void proceedTransaction(Transaction incomingTransaction) {
		if (incomingTransaction.getStatus() == Status.IN_PROCESS) {
		Transaction proceededTransaction = bankService.book(incomingTransaction);
		bankJMSProducer.sendTransaction(proceededTransaction);
		} else {
			// das is eine antwort an uns
		}

	}

}
