package com.accenture.frameworks;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fw.springboot.bank.BankAccount;
import fw.springboot.bank.BankAccountRepository;
import fw.springboot.bank.BankApplication;
import fw.springboot.bank.BankServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BankApplication.class })
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
		Assert.assertEquals("S"+bAccRep.count(), num);
	}

	@Test
	public void testCreateAccountAndGetItFromDb() {

		bankAccount = bankServiceImpl.createAccount();
		bankAccount = bankServiceImpl.createAccount();
		String num = bankAccount.getAccountNumber();
		BigDecimal balance1 = bankAccount.getBalance();

		acc = bankServiceImpl.getOneAccount(num);
		Assert.assertEquals(num, acc.getAccountNumber());
		Assert.assertEquals(balance1.setScale(0, RoundingMode.HALF_UP), acc.getBalance().setScale(0, RoundingMode.HALF_UP));
	}

}
