package fw.springboot.bank;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GetAOP {
	@Autowired
	AccountNumberChecker accountNumberChecker;
	
	@Before("execution(* fw.springboot.bank.BankService.getBalance(..))")
	public void logBefore(JoinPoint joinPoint) throws Exception {
		
		String accountNumber = (String) joinPoint.getArgs()[0];

		if(!accountNumberChecker.checkAccountNumerIfFromOurBank(accountNumber)){
			throw new Exception();
		}
	}
	
}
