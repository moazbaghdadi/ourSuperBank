package com.accenture.frameworks;

import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fw.springboot.bank.ATMController;
import fw.springboot.bank.BankAccount;
import fw.springboot.bank.BankApplication;
import fw.springboot.bank.BankServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BankApplication.class })

public class GetBalanceTest {

	@Autowired
	BankServiceImpl bankServiceImpl;
	BankAccount bankAccount;
	
	@Autowired
	ATMController atmCtrl;

	@Before
	public void init() {
		bankAccount = bankServiceImpl.createAccount();
	}

	@Test
	public void getBalance() throws Exception {

		BigDecimal balanceResult = bankServiceImpl.getBalance(bankAccount.getAccountNumber());

		Assert.assertEquals(0.0, balanceResult.doubleValue(), 0);

	}

	@Test
	public void getBalanceShouldThrowException() {

		try {
			bankServiceImpl.getBalance("X4526356");
			fail("Should not be reached");
		} catch (Exception e) {
		}
	}

	@Test
	public void getBalanceWithATMControllerShouldSucceed() {
		BigDecimal balanceResult = atmCtrl.getBalance(bankAccount.getAccountNumber());

		Assert.assertEquals(0.0, balanceResult.doubleValue(), 0);
	}

	@Test
	public void getBalanceWithATMControllerShouldThrowException() {
		try {
			atmCtrl.getBalance("X4526356");
			fail("Should not be reached");
		} catch (Exception e) {
		}
	}

}
