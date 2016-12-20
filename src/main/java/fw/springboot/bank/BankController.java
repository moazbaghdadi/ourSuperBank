package fw.springboot.bank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

	@Autowired
	BankService bankService;
	
	@RequestMapping(value = "/accounts/{accountNumber}", method = RequestMethod.GET)
	public BankAccount getOneAccount(@PathVariable("accountNumber") String accountNumber) {
		// find account by number in db
		BankAccount account = bankService.getOneAccount(accountNumber);
		return account;
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public List<BankAccount> getAllAccounts() {
		// find account by number in db

		return (List<BankAccount>) bankService.getAllAccounts();
	}

	@RequestMapping(path = "/accounts", method = RequestMethod.POST)
	public BankAccount createAccount() {
		return bankService.createAccount();
	}
}
