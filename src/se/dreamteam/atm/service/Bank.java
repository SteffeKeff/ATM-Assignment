package se.dreamteam.atm.service;

import se.dreamteam.atm.model.BankReceipt;

public interface Bank
{
	String getBankId();
	
	long getBalance(final String accountHolderId);
	
	long withdrawAmount(final int amount);
	
	BankReceipt requestReceipt(final long transactionId);
}