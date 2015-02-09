package se.dreamteam.atm.service;

import se.dreamteam.atm.exception.ATMException;
import se.dreamteam.atm.model.ATMCard;
import se.dreamteam.atm.model.ATMReceipt;
import se.dreamteam.atm.model.BankReceipt;

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
		return bank.withdrawAmount(amount);
	}

	@Override
	public ATMReceipt requestReceipt(long transactionId)
	{
		BankReceipt bankReceipt = bank.requestReceipt(transactionId);
		return new ATMReceipt(bankReceipt.getTransactionId(), bankReceipt.getAmount(), bankReceipt.getDate());
	}

	@Override
	public long checkBalance()
	{
		return bank.getBalance(atmCard.getAccountHolderId());
	}

}
