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

public class GetBalanceTest {

	@Autowired
	BankServiceImpl bankServiceImpl;
	BankAccount bankAccount; 
	
	@Before
	public void init() {
		bankAccount = bankServiceImpl.createAccount();
	}

	@Test
	public void getBalance() throws Exception {

		BigDecimal balanceResult = bankServiceImpl.getBalance(bankAccount.getAccountNumber());

		Assert.assertEquals(0.0, balanceResult.doubleValue(), 0);

	}

	// @Test
	// public void getBalanceFromWrongAccount() throws Exception {
	//
	// BigDecimal balanceResult =
	// bankServiceImpl.getBalance(bankAccount.getAccountNumber());
	//
	// // Assert.assertFalse(new BigDecimal(0).equals(balanceResult));
	// Assert.assertNull(balanceResult);
	//
	// }

}
