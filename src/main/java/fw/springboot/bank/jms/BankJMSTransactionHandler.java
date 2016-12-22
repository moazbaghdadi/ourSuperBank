package fw.springboot.bank.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fw.springboot.bank.BankService;
import fw.springboot.bank.Transaction;

@Component
public class BankJMSTransactionHandler {

	@Autowired
	BankService bankService;

	@Autowired
	BankJMSProducer bankJMSProducer;

	public BankJMSTransactionHandler() {
	}

	public void proceedTransaction(Transaction incomingTransaction) {
		Transaction proceededTransaction = bankService.book(incomingTransaction);
		bankJMSProducer.sendTransaction(proceededTransaction);

	}

}
