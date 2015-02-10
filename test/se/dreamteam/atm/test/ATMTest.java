package se.dreamteam.atm.test;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import se.dreamteam.atm.exception.ATMException;
import se.dreamteam.atm.exception.ATMSecurityException;
import se.dreamteam.atm.model.ATMCard;
import se.dreamteam.atm.model.ATMReceipt;
import se.dreamteam.atm.model.BankReceipt;
import se.dreamteam.atm.service.ATM;
import se.dreamteam.atm.service.ATMSession;
import se.dreamteam.atm.service.Bank;

@RunWith(MockitoJUnitRunner.class)
public final class ATMTest
{

	private ATM atm;
	private List<Bank> banks;
	private ATMCard atmCard1;
	private ATMCard atmCard2;
	private String bankId = "SEB";
	private String invalidBank = "SBAB";
	private String accountHolderId1 = "Stefan";
	private int correctPin = 1234;
	private int invalidPin = 1111;

	@Mock
	private Bank bank;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public final void setUp()
	{
		when(bank.getBankId()).thenReturn(bankId);
		when(bank.getBalance(accountHolderId1)).thenReturn(3000L);

		banks = new ArrayList<>();
		banks.add(bank);
		atm = new ATM(banks);

		atmCard1 = new ATMCard(accountHolderId1, bank.getBankId(), correctPin);
		atmCard2 = new ATMCard(accountHolderId1, invalidBank, correctPin);
	}

	@After
	public final void tearDown()
	{
		reset(bank);
	}

	@Test
	public final void shouldThrowATMExceptionIfValueLowerThan100()
	{
		expectedException.expect(ATMException.class);
		expectedException.expectMessage("The amount is not valid");

		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);
		ATMSI.withdrawAmount(90);
	}

	@Test
	public final void shouldThrowATMExceptionIfValueBiggerThan10000()
	{
		expectedException.expect(ATMException.class);
		expectedException.expectMessage("The amount is not valid");

		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);
		ATMSI.withdrawAmount(10001);
	}

	@Test
	public final void shouldThrowATMExceptionifValueIsNotEven()
	{
		expectedException.expect(ATMException.class);
		expectedException.expectMessage("The amount is not valid");

		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);
		ATMSI.withdrawAmount(189);
	}

	@Test
	public final void ATMSecurityExceptionThrownWhenInvalidPinCode()
	{
		expectedException.expect(ATMSecurityException.class);
		expectedException.expectMessage("Invalid pincode");

		atm.verifyPin(invalidPin, atmCard1);
	}

	@Test
	public final void ATMExceptionThrownWhenInvalidBank()
	{
		expectedException.expect(ATMException.class);
		expectedException.expectMessage("Bank does not exist");

		atm.verifyPin(correctPin, atmCard2);
	}

	@Test
	public final void ATMExceptionThrownWhenWithdrawAmountCalledTwice()
	{
		expectedException.expect(ATMException.class);
		expectedException.expectMessage("ATM Session has expired");

		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);
		ATMSI.withdrawAmount(1000);
		ATMSI.withdrawAmount(1500);
	}
	
	@Test
	public final void ATMExceptionThrownWhenCheckBalanceCalledTwice()
	{
		expectedException.expect(ATMException.class);
		expectedException.expectMessage("ATM Session has expired");

		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);
		ATMSI.checkBalance();
		ATMSI.checkBalance();
	}

	@Test
	public final void IllegalArgumentExceptionThrownWhenATMInitiatedWithEmptyArray()
	{
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Bank list is empty");

		atm = new ATM(new ArrayList<Bank>());
	}

	@Test
	public final void ATMExceptionThrownWhenLargerAmountWithdrawnThanBalance()
	{
		expectedException.expect(ATMException.class);
		expectedException.expectMessage("The amount is not valid");

		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);
		ATMSI.withdrawAmount(3001);
	}

	@Test
	public final void shouldReturnValidReceipt()
	{
		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);
		ATMSI.withdrawAmount(100);
		final long transactionId = ATMSI.getTransactionId();

		when(bank.requestReceipt(transactionId)).thenReturn(new BankReceipt(bankId, transactionId, 100));

		assertThat("ATMReceipt should contain valid transactionId",
				ATMSI.requestReceipt(transactionId).getTransactionId(),
				is(new ATMReceipt(transactionId, 100).getTransactionId()));

		assertThat("ATMReceipt should contain valid amount",
				ATMSI.requestReceipt(transactionId).getAmount(),
				is(new ATMReceipt(transactionId, 100).getAmount()));

		assertThat("ATMReceipt should contain valid date",
				ATMSI.requestReceipt(transactionId).getDate(),
				is(new ATMReceipt(transactionId, 100).getDate()));

		verify(bank, times(3)).requestReceipt(transactionId);
	}

	@Test
	public final void shouldReturnValidBalance()
	{
		ATMSession ATMSI = atm.verifyPin(correctPin, atmCard1);

		when(bank.getBalance(accountHolderId1)).thenReturn((long) 100);

		assertThat("should return valid balance", ATMSI.checkBalance(), is((long) 100));

		verify(bank).getBalance(accountHolderId1);
	}

}