package se.dreamteam.atm.service;

import se.dreamteam.atm.model.ATMCard;

public abstract class AbstractATMSession implements ATMSession
{
	protected final ATMCard atmCard;
	protected final Bank bank;
	
	public AbstractATMSession(final ATMCard atmCard, final Bank bank)
	{
		this.atmCard = atmCard;
		this.bank = bank;
	}
}