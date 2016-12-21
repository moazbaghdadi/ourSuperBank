package com.accenture.frameworks;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fw.springboot.bank.ATMController;
import fw.springboot.bank.BankAccount;
import fw.springboot.bank.BankApplication;
import fw.springboot.bank.BankService;
import fw.springboot.bank.BankServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankApplication.class })
public class DepositTest {
	@Autowired
	BankServiceImpl bankServiceImpl;
	BankAccount bankAccount;
	BankService bankService;
	@Autowired
	ATMController atmController;

	@Before
	public void init() {
		bankAccount = bankServiceImpl.createAccount();

	}

	
	@Test
	public void testBookMethodToReturnBalance() throws Exception {

		BigDecimal result = bankServiceImpl.book(bankAccount.getAccountNumber(), new BigDecimal(3.0));

		Assert.assertEquals(new BigDecimal(3).setScale(0, RoundingMode.HALF_UP), result.setScale(0, RoundingMode.HALF_UP));
	}


	@Test
	public void testBookMethodWithBigDecimal() throws Exception {

		BigDecimal result = bankServiceImpl.book(bankAccount.getAccountNumber(), new BigDecimal(3.175));

		Assert.assertEquals(new BigDecimal(3.175).setScale(0, RoundingMode.HALF_UP), result.setScale(0, RoundingMode.HALF_UP));

	}

	@Test
	public void testBookMethodWithTwoBookings() throws Exception {
		BigDecimal result = bankServiceImpl.book(bankAccount.getAccountNumber(), new BigDecimal(3.175));
		 result = bankServiceImpl.book(bankAccount.getAccountNumber(), new BigDecimal(1.825));

		Assert.assertEquals(new BigDecimal(5.0), result.setScale(0, RoundingMode.HALF_UP));

	}
	
	@Test
	public void testDepositMethod() throws Exception {
		BigDecimal result = atmController.deposit(bankAccount.getAccountNumber(), new BigDecimal(2.5));
		Assert.assertEquals(new BigDecimal(2.5).setScale(0, RoundingMode.HALF_UP), result.setScale(0, RoundingMode.HALF_UP));
		
	}
	
	@Test
	public void testWithdrawalMethod() throws Exception {
		BigDecimal result = atmController.withdrawal(bankAccount.getAccountNumber(), new BigDecimal(4.5));
		Assert.assertEquals(new BigDecimal(-4.5).setScale(0, RoundingMode.HALF_UP), result.setScale(0, RoundingMode.HALF_UP));
	
		
	}
	

	
	
}