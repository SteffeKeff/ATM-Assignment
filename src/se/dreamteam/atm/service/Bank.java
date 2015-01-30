package se.dreamteam.atm.service;

public interface Bank
{
	String getBankId();
	long getBalance(String accountHolderId);
}
