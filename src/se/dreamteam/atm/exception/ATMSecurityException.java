package se.dreamteam.atm.exception;

public final class ATMSecurityException extends RuntimeException
{
	private static final long serialVersionUID = -3031452609540350355L;

	public ATMSecurityException(final String message, final Throwable clause)
	{
		super(message, clause);
	}

	public ATMSecurityException(final String message)
	{
		super(message);
	}
}