package se.dreamteam.atm.service;

import se.dreamteam.atm.model.ATMReceipt;

public interface ATMSession
{
	long withdrawAmount(final int amount);
	
	ATMReceipt requestReceipt(final long transactionId);
	
	long checkBalance();
	
	long getTransactionId();
}
