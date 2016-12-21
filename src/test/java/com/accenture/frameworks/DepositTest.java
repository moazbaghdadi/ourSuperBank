package com.accenture.frameworks;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fw.springboot.bank.BankAccount;
import fw.springboot.bank.BankApplication;
import fw.springboot.bank.BankServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BankApplication.class })
public class DepositTest {
	@Autowired
	BankServiceImpl bankServiceImpl;
	BankAccount bankAccount;

	@Before
	public void init() {
		bankAccount = bankServiceImpl.createAccount();

	}

	@Test
	public void testDeposit() throws Exception {

		BigDecimal result = bankServiceImpl.book(bankAccount.getAccountNumber(), new BigDecimal(3.0));

		Assert.assertEquals(new BigDecimal(3.0).toBigInteger(), result.toBigInteger());
	}

	// @Test
	// public void testWithdrawal() {
	// Account account1 = new Account(1);
	// boolean result = bank.withdrawal(account1, 30.175);
	//
	// // System.out.println(result);
	// Assert.assertFalse(result);
	//
	// Assert.assertTrue(bank.deposit(account1, 100));
	// Assert.assertTrue(bank.withdrawal(account1, 30));

}
