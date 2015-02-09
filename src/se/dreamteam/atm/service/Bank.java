package se.dreamteam.atm.service;

import se.dreamteam.atm.model.BankReceipt;

public interface Bank
{
	String getBankId();
	long getBalance(String accountHolderId);
	long withdrawAmount(int amount);
	BankReceipt requestReceipt(long transactionId);
}
