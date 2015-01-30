package se.dreamteam.atm.service;

import se.dreamteam.atm.model.ATMCard;

public abstract class AbstractATMSession
{
	protected final ATMCard atmCard;
	protected final Bank bank;
	
	public AbstractATMSession(ATMCard atmCard, Bank bank)
	{
		this.atmCard = atmCard;
		this.bank = bank;
	}
}
