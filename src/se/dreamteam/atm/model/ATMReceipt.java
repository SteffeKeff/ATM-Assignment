package se.dreamteam.atm.model;

import java.util.Date;

public final class ATMReceipt
{
	private final long transactionId;
	private final int amount;
	private final Date date;

	public ATMReceipt(final long transactionId, final int amount)
	{
		this.transactionId = transactionId;
		this.amount = amount;
		this.date = new Date();
	}

	public long getTransactionId()
	{
		return transactionId;
	}

	public int getAmount()
	{
		return amount;
	}

	public Date getDate()
	{
		return date;
	}

	@Override
	public String toString()
	{
		return "ATMReceipt [transactionId=" + transactionId + ", amount=" + amount + ", date=" + date + "]";
	}

}
