package fw.springboot.bank.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import fw.springboot.bank.Transaction;

@Component
public class BankJMSProducer {

	JmsTemplate jmsTemplate;

	@Value("${bank.queue.send.name}")
	private String queueName;

	private BankJMSProducer() {
	}

	@Autowired
	public BankJMSProducer(JmsTemplate jmsTemplate) {
		super();
		this.jmsTemplate = jmsTemplate;
	}

	public void sendTransaction(final Transaction sendingTransaction) {
		jmsTemplate.convertAndSend(queueName, sendingTransaction);
	}

}
