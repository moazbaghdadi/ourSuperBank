package fw.springboot.bank;

import org.springframework.beans.factory.annotation.Value;

public class AccountNumberChecker {

	@Value("${bank.prefix}")
	private static String accountNumberPrefix;

	static boolean checkAccountNumerIfFromOurBank(String accountNumber) {

		return accountNumber.startsWith(accountNumberPrefix);

	}
}
