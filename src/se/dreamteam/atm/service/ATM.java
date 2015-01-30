package se.dreamteam.atm.service;

import java.util.List;
import java.util.Map;

public final class ATM
{
	private final Map<String, Bank> banks;
	
	public ATM(List<Bank> banks)
	{
		//To be implemented
	}
	
	public ATMSession verifyPin(int pin, ATMCard card)
	{
		//To be implemented
	}
	
	private Bank getBank(ATMCard card){
		//To be implemented
	}
	
}
