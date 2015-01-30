package se.dreamteam.atm.exception;

public class ATMException extends RuntimeException
{
	private static final long serialVersionUID = 9187187163143195697L;

	public ATMException(String message, Throwable clause)
	{
		super(message, clause);
	}

	public ATMException(String message)
	{
		super(message);
	}
}
