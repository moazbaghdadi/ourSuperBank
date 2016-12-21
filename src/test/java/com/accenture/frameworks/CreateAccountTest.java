package com.accenture.frameworks;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fw.springboot.bank.BankAccount;
import fw.springboot.bank.BankAccountRepository;
import fw.springboot.bank.BankServiceImpl;
import fw.springboot.bank.SpringbootDemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringbootDemoApplication.class })
public class CreateAccountTest {

	@Autowired
	BankServiceImpl bankServiceImpl;
	BankAccount bankAccount, acc, acc2;
	@Autowired
	BankAccountRepository bAccRep;

	@Test
	public void testCreateAccountNotNull() {

		bankAccount = bankServiceImpl.createAccount();
		Integer accId = bankAccount.getId();
		Assert.assertNotNull(bankAccount);
		// System.out.println(acc.getAccountNumber());
		Assert.assertEquals(bAccRep.count(), (long) bankAccount.getId());
	}


	@Test
	public void testCreateTwoAccount() {

		bankAccount = bankServiceImpl.createAccount();
		bankAccount = bankServiceImpl.createAccount();
		String num = bankAccount.getAccountNumber();
		// System.out.println("Hier Test " + num);
		Assert.assertEquals("SB2", num);
	}

	@Test
	public void testCreateAccountAndGetItFromDb() {

		bankAccount = bankServiceImpl.createAccount();
		bankAccount = bankServiceImpl.createAccount();
		String num = bankAccount.getAccountNumber();
		BigDecimal balance1 = bankAccount.getBalance();

		acc = bankServiceImpl.getOneAccount(num);
		Assert.assertEquals(num, acc.getAccountNumber());
		Assert.assertEquals(balance1, acc.getBalance());
	}

}
