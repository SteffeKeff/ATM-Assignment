package se.dreamteam.atm.service;

import se.dreamteam.atm.exception.ATMException;
import se.dreamteam.atm.model.ATMCard;
import se.dreamteam.atm.model.ATMReceipt;

public class ATMSessionImpl extends AbstractATMSession
{

	public ATMSessionImpl(ATMCard atmCard, Bank bank)
	{
		super(atmCard, bank);
	}

	@Override
	public long withdrawAmount(int amount)
	{
		if(amount < 100 || amount > 10000 || amount%100 != 0){
			throw new ATMException("The amount is not valid");
		}
		return 0; //Ska returnera saldo
	}

	@Override
	public ATMReceipt requestReceipt(long transactionId)
	{
		return null;
	}

	@Override
	public long checkBalance()
	{
		return 0;
	}

}
