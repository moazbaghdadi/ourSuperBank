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
import fw.springboot.bank.Transaction;
import fw.springboot.bank.Transaction.Status;


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
	public void testBookMethodWithBookMethodDepositeShouldSucceed() throws Exception {
		
		Transaction tx = new Transaction(null, bankAccount.getAccountNumber(), new BigDecimal(3.0));

		Transaction txResult = bankServiceImpl.book(tx);

		assertEquals(txResult.getStatus(), Transaction.Status.FINISHED);
		
		BankAccount bankAccountActualisiert = bankServiceImpl.getOneAccount(bankAccount.getAccountNumber());
		
		assertEquals(new BigDecimal(3.0).setScale(0, RoundingMode.HALF_UP), bankAccountActualisiert.getBalance().setScale(0, RoundingMode.HALF_UP));
	}

	@Test
	public void testBookMethodWithBigDecimal() throws Exception {
		Transaction tx = new Transaction(null, bankAccount.getAccountNumber(), new BigDecimal(3.175));
		Transaction txresult = bankServiceImpl.book(tx);

		assertEquals(txresult.getStatus(), Transaction.Status.FINISHED);

		BankAccount bankAccountActualisiert = bankServiceImpl.getOneAccount(bankAccount.getAccountNumber());

		Assert.assertEquals(new BigDecimal(3.175).setScale(0, RoundingMode.HALF_UP), bankAccountActualisiert.getBalance().setScale(0, RoundingMode.HALF_UP));

	}

	@Test
	public void testBookMethodWithTwoBookings() throws Exception {
		Transaction tx = new Transaction(null, bankAccount.getAccountNumber(), new BigDecimal(3.175));
		Transaction txresult = bankServiceImpl.book(tx);
		
		assertEquals(txresult.getStatus(), Transaction.Status.FINISHED);

		tx = new Transaction(null, bankAccount.getAccountNumber(), new BigDecimal(1.825));
		txresult = bankServiceImpl.book(tx);

		assertEquals(txresult.getStatus(), Transaction.Status.FINISHED);

		BankAccount bankAccountActualisiert = bankServiceImpl.getOneAccount(bankAccount.getAccountNumber());

		Assert.assertEquals(new BigDecimal(5.0).setScale(0, RoundingMode.HALF_UP), bankAccountActualisiert.getBalance().setScale(0, RoundingMode.HALF_UP));

	}
	
	@Test
	public void testDepositMethod() throws Exception {
		Transaction tx = new Transaction(null, bankAccount.getAccountNumber(), new BigDecimal(4));
		Status statusResult = atmController.deposit(tx.getToAccount(), tx.getAmount());

		assertEquals(statusResult, Transaction.Status.FINISHED);

		BankAccount bankAccountActualisiert = bankServiceImpl.getOneAccount(bankAccount.getAccountNumber());

		Assert.assertEquals(new BigDecimal(4.0).setScale(0, RoundingMode.HALF_UP), bankAccountActualisiert.getBalance().setScale(0, RoundingMode.HALF_UP));
	}
	
	@Test
	public void testWithdrawalMethod() throws Exception {
		Transaction tx = new Transaction(bankAccount.getAccountNumber(), null, new BigDecimal(4));
		Status statusResult = atmController.deposit(tx.getFromAccount(), new BigDecimal(10000));

		assertEquals(statusResult, Transaction.Status.FINISHED);

		statusResult = atmController.withdrawal(tx.getFromAccount(), tx.getAmount());

		assertEquals(statusResult, Transaction.Status.FINISHED);

		BankAccount bankAccountActualisiert = bankServiceImpl.getOneAccount(bankAccount.getAccountNumber());

		Assert.assertEquals(new BigDecimal(9996.0).setScale(0, RoundingMode.HALF_UP), bankAccountActualisiert.getBalance().setScale(0, RoundingMode.HALF_UP));
	}
}