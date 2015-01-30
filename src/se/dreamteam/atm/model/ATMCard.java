package se.dreamteam.atm.model;

public class ATMCard
{
	private final String accountHolderId;
	private final String bankId;
	private final int pin;

	public ATMCard(String accountHolderId, String bankId, int pin)
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

	public boolean verifyPin(int pin)
	{
		return this.pin == pin;
	}
}
