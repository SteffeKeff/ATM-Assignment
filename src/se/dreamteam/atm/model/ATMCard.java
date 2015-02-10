package se.dreamteam.atm.model;

public final class ATMCard
{
	private final String accountHolderId;
	private final String bankId;
	private final int pin;

	public ATMCard(final String accountHolderId, final String bankId, final int pin)
	{
		this.accountHolderId = accountHolderId;
		this.bankId = bankId;
		this.pin = pin;
	}

	public String getAccountHolderId()
	{
		return accountHolderId;
	}

	public String getBankId()
	{
		return bankId;
	}

	public boolean verifyPin(final int pin)
	{
		return this.pin == pin;
	}
}