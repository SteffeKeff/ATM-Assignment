package se.dreamteam.atm.service;

import se.dreamteam.atm.model.ATMReceipt;

public interface ATMSession
{
	long withdrawAmount(int amount);
	
	ATMReceipt requestReceipt(long transactionId);
	
	long checkBalance();
}
