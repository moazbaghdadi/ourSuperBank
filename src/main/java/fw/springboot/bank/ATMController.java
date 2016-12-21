package fw.springboot.bank;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {
	
	@Autowired
	private BankService bankService;
	
	//Methode getBalance
	@RequestMapping(value="/balance/{accountNumber}", method=RequestMethod.GET)
	public BigDecimal getBalance (@PathVariable("accountNumber") String accountNumber){
		return bankService.getBalance(accountNumber);
	}
	
	//Methode deposit
	@RequestMapping(value="/deposit/{accountNumber}/{amount}", method=RequestMethod.POST)
	public BigDecimal deposit (@PathVariable("accountNumber")String accountNumber, @PathVariable("amount")BigDecimal amount){
		return bankService.book(accountNumber, amount);
	}
	
	//Methode withdrawal
	@RequestMapping(value="/withdrawal/{accountNumber}/{amount}", method=RequestMethod.POST)
	public BigDecimal withdrawal (@PathVariable("accountNumber") String accountNumber, @PathVariable("amount") BigDecimal amount){
		return bankService.book(accountNumber, amount.negate());
	}

}
