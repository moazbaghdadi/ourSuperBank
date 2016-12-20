package fw.springboot.bank;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {
	
	@Autowired
	private BankService bankService;
	
	//Methode getBalance
	public BigDecimal getBalance (String accountNumber){
		return bankService.getBalance(accountNumber);
	}
	
	//Methode deposit
	public BigDecimal deposit (String accountNumber, BigDecimal amount){
		return bankService.book(accountNumber, amount);
	}
	
	//Methode withdrawal
	public BigDecimal withdrawal (String accountNumber,  BigDecimal amount){
		return bankService.book(accountNumber, amount);
	}

}
