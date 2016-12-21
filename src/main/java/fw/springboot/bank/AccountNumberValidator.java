package fw.springboot.bank;

import org.springframework.beans.factory.annotation.Value;

public class AccountNumberValidator {

	@Value("${bank.prefix}")
	private static String accountNumberPrefix;

	static boolean validateAccountNumber(String accountNumber) {

		return accountNumber.startsWith(accountNumberPrefix);

	}
}
