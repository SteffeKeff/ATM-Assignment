package se.dreamteam.atm.exception;

public final class ATMException extends RuntimeException
{
	private static final long serialVersionUID = 9187187163143195697L;

	public ATMException(final String message, final Throwable clause)
	{
		super(message, clause);
	}

	public ATMException(final String message)
	{
		super(message);
	}
}