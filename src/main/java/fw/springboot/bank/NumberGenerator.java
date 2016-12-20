package fw.springboot.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NumberGenerator {

	private int accountNumber = 0;
	@Value("${bank.prefix}")
	private String accountNumberPrefix;

	public String getNextNumber() {
		accountNumber++;

		return accountNumberPrefix + accountNumber;

	}

}
