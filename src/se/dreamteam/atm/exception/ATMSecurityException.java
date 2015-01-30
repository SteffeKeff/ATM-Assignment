package se.dreamteam.atm.exception;

public class ATMSecurityException extends RuntimeException
{
	private static final long serialVersionUID = -3031452609540350355L;
	
	public ATMSecurityException(String message, Throwable clause)
	{
		super(message, clause);
	}

	public ATMSecurityException(String message)
	{
		super(message);
	}
	
}
