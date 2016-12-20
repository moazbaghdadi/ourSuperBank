package fw.springboot.bank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
	
	@Autowired
	private BankAccountRepository accountRepository;
	
	@RequestMapping(value="/accounts/{accountNumber}", method=RequestMethod.GET)
	public BankAccount getOneAccount(@PathVariable("accountNumber") int accountNumber) {
		//find account by number in db
		BankAccount account = accountRepository.findBankAccountByAccountNumber(accountNumber);
		return account;
	}
	
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public List<BankAccount> getAllAccount() {
		//find account by number in db
		
		return (List<BankAccount>) accountRepository.findAll();
	}
	
	@RequestMapping(path="/accounts", method=RequestMethod.POST)
	public BankAccount saveAccount(@RequestBody BankAccount account) {
		return accountRepository.save(account);
	}
}
