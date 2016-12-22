package fw.springboot.bank;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fw.springboot.bank.jms.BankJMSProducer;

@Aspect
@Component
public class BookAOP {
	@Autowired
	AccountNumberChecker accountNumberChecker;
	
	@Autowired
	BankJMSProducer bankJMSProducer;

	@Around("execution(* fw.springboot.bank.BankService.book(..))")
	public Transaction logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		Transaction tx = (Transaction) joinPoint.getArgs()[0];
		
		if (tx.getFromAccount() == null && tx.getToAccount() == null) {
			tx.setStatus(Transaction.Status.FAILED);
			return tx;
		}

		if (tx.getFromAccount() == null) {

			if (accountNumberChecker.checkAccountNumerIfFromOurBank(tx.getToAccount())) {
				return (Transaction) joinPoint.proceed();
			} else {
				// TODO use JMS to send
				bankJMSProducer.sendTransaction(tx);

				tx.setStatus(Transaction.Status.FINISHED);
				return tx;
			}

		} else {
			if (accountNumberChecker.checkAccountNumerIfFromOurBank(tx.getFromAccount())) {
				return (Transaction) joinPoint.proceed();
			} else {
				// TODO use JMS to send
				bankJMSProducer.sendTransaction(tx);

				tx.setStatus(Transaction.Status.FINISHED);
				return tx;
			}

		}
		
	}
	
}
