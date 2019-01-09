/*
* Conditions Of Use
*
* This software was developed by employees of the Sigmatrix(Beijing) Corporation.
* This software is provided by sigmatrix as a service and is expressly
* provided "AS IS."  Sigmatrix MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
* OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
* AND DATA ACCURACY.  Sigmatrix does not warrant or make any representations
* regarding the use of the software or the results thereof, including but
* not limited to the correctness, accuracy, reliability or usefulness of
* the software.
*
* Permission to use this software is contingent upon your acceptance
* of the terms of this agreement.
*
*/
package com.sigmatrix.exception;

/**
 * <p>
 * SigmatrixDaoException is for all dao components.
 * If you want to define your own exception,extend this class.
 * </p>
 * 
 * <p>
 * Concrete exceptions should be business related such as UserNotFoundException etc.
 * </p>
 * @author Qiang
 *
 */
@SuppressWarnings({"PMD"})
public class SigmatrixDaoException extends Exception {

	private static final long serialVersionUID = -8614839583820073194L;
	
	/**
	 * Default Constructor.
	 */
	public SigmatrixDaoException() {
		super();
	}
	
	/**
	 * Constructor for passing message, cause and flags enableSuppression and writableStackTrace to indicate allowable operation.
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SigmatrixDaoException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	/**
	 * Constructor for passing message and cause.
	 * 
	 * @param message
	 * @param cause
	 */
	public SigmatrixDaoException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Construcor for passing message.
	 * 
	 * @param message
	 */
	public SigmatrixDaoException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor for passing cause.
	 * 
	 * @param cause
	 */
	public SigmatrixDaoException(final Throwable cause) {
		super(cause);
	}
}
