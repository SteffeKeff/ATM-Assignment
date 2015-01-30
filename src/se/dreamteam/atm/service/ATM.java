package se.dreamteam.atm.service;

import java.util.List;
import java.util.Map;

import se.dreamteam.atm.exception.ATMException;
import se.dreamteam.atm.exception.ATMSecurityException;
import se.dreamteam.atm.model.ATMCard;

public final class ATM
{
	private final Map<String, Bank> banks;

	public ATM(List<Bank> banks)
	{
		// To be implemented
	}

	public ATMSession verifyPin(int pin, ATMCard card)
	{
		if (card.verifyPin(pin))
		{
			return new ATMSessionImpl(card, getBank(card));
		}
		throw new ATMSecurityException("Invalid pincode");
	}

	private Bank getBank(ATMCard card)
	{
		if (banks.containsKey(card.getBankId()))
		{
			return banks.get(card.getBankId());
		}
		throw new ATMException("Bank does not exist");
	}

}
