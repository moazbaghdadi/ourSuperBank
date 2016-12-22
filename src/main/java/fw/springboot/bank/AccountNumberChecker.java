package fw.springboot.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountNumberChecker {

	@Value("${bank.prefix}")
	private String accountNumberPrefix;

	boolean checkAccountNumerIfFromOurBank(String accountNumber) {

		return accountNumber.startsWith(accountNumberPrefix);

	}
}
