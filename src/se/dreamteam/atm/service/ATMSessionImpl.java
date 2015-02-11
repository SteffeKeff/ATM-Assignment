package se.dreamteam.atm.service;

import java.util.Date;

import se.dreamteam.atm.exception.ATMException;
import se.dreamteam.atm.model.ATMCard;
import se.dreamteam.atm.model.ATMReceipt;

public final class ATMSessionImpl extends AbstractATMSession
{
	private long transactionID;
	private final Date date;

	public ATMSessionImpl(final ATMCard atmCard, final Bank bank)
	{
		super(atmCard, bank);
		date = new Date();
	}

	@Override
	public long withdrawAmount(final int amount)
	{
		if (isSessionValid())
		{
			if (amount >= 100 && amount <= 10000 && amount % 100 == 0)
			{
				if (bank.getBalance(atmCard.getAccountHolderId()) > amount)
				{
					transactionID = hashCode();		
					return bank.withdrawAmount(amount);
				}
				throw new ATMException("Not enough funds");
			}
			throw new ATMException("The amount is not valid");
		}
		throw new ATMException("ATM Session has expired");
	}

	@Override
	public long checkBalance()
	{
		if (isSessionValid())
		{
			transactionID = hashCode();
			return bank.getBalance(atmCard.getAccountHolderId());
		}
		throw new ATMException("ATM Session has expired");
	}
	
	private boolean isSessionValid()
	{
		return transactionID == 0;
	}

	@Override
	public ATMReceipt requestReceipt(final long transactionId)
	{
		return new ATMReceipt(transactionId, bank.requestReceipt(transactionId).getAmount());
	}

	@Override
	public long getTransactionId()
	{
		if(transactionID != 0) { return transactionID; }
		throw new ATMException("Not valid request");
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		ATMSessionImpl other = (ATMSessionImpl) obj;
		if (date == null)
		{
			if (other.date != null) return false;
		}
		else if (!date.equals(other.date)) { return false; }
		return true;
	}
}