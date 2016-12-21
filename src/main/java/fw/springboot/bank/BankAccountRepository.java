package fw.springboot.bank;

import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

	BankAccount findBankAccountByAccountNumber(String accountNumber);
}
