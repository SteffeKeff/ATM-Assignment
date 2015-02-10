package se.dreamteam.atm.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import se.dreamteam.atm.model.ATMCard;
import se.dreamteam.atm.model.ATMReceipt;
import se.dreamteam.atm.model.BankReceipt;
import se.dreamteam.atm.service.ATM;
import se.dreamteam.atm.service.ATMSession;
import se.dreamteam.atm.service.Bank;

@RunWith(MockitoJUnitRunner.class)
public final class ATMSessionImplTest
{
	private ATM atm;
	private List<Bank> banks;
	private ATMCard card;
	private String bankId = "SEB";
	private String accountHolderId1 = "Stefan";
	private int pin = 1234;
	private int amount;
	private int transactionId;
	private BankReceipt bankReceipt;

	@Mock
	private Bank bank;

	@Before
	public final void setUp()
	{
		when(bank.getBankId()).thenReturn(bankId);
		when(bank.getBalance(accountHolderId1)).thenReturn(3000L);
		when(bank.withdrawAmount(amount)).thenReturn((long) amount);
		when(bank.requestReceipt(transactionId)).thenReturn(bankReceipt);

		card = new ATMCard(accountHolderId1, bank.getBankId(), pin);
		banks = new ArrayList<>();
		banks.add(bank);
		atm = new ATM(banks);
	}

	@After
	public final void tearDown()
	{
		reset(bank);
	}

	@Test
	public final void withdrawAmountTest()
	{
		int amount = 100;
		ATMSession session;
		session = atm.verifyPin(pin, card);
		long returnedValue = session.withdrawAmount(100);

		assertThat((long) amount, is(returnedValue));
		verify(bank, times(1)).withdrawAmount(100);
	}
	
	@Test
	public final void requestRecieptTest()
	{
		int amount = 1000;
		ATMSession session;
		session = atm.verifyPin(pin, card);
		long returnedValue = session.withdrawAmount(100);
		
		transactionId = (int) session.getTransactionId();
		System.out.println("Transaction id : " + transactionId);
		ATMReceipt atmr = session.requestReceipt(transactionId);
		System.out.println(atmr.getAmount() + " " + atmr.getTransactionId() +" "+ atmr.getDate());
		
		assertThat(returnedValue, is((long)100));
		verify(bank).withdrawAmount(100);
	}

}
