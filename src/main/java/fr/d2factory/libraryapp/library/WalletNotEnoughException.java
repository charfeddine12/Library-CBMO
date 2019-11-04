package fr.d2factory.libraryapp.library;

/**
 * This exception is thrown when a member doesn't have the required amount of money in his wallet.
 */
public class WalletNotEnoughException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WalletNotEnoughException() {
		super();
	}

	public WalletNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}

	public WalletNotEnoughException(String message) {
		super(message);
	}

	public WalletNotEnoughException(Throwable cause) {
		super(cause);
	}

}
