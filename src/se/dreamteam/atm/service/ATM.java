package se.dreamteam.atm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.dreamteam.atm.exception.ATMException;
import se.dreamteam.atm.exception.ATMSecurityException;
import se.dreamteam.atm.model.ATMCard;

public final class ATM
{
	private final Map<String, Bank> banks = new HashMap<>();

	public ATM(final List<Bank> banks)
	{
		if (banks.isEmpty())
		{
			throw new IllegalArgumentException("Bank list is empty");
		}

		for (Bank bank : banks)
		{
			this.banks.put(bank.getBankId(), bank);
		}
	}

	public ATMSession verifyPin(final int pin, final ATMCard card)
	{
		if (card.verifyPin(pin))
		{
			return new ATMSessionImpl(card, getBank(card));
		}
		throw new ATMSecurityException("Invalid pincode");
	}

	private Bank getBank(final ATMCard card)
	{
		if (banks.containsKey(card.getBankId()))
		{
			return banks.get(card.getBankId());
		}
		throw new ATMException("Bank does not exist");
	}
}