package com.aotain.tdc.exception;

public class NoAuthenticatedUserException extends RuntimeException {

	private static final long serialVersionUID = 3754879553526236354L;

	public NoAuthenticatedUserException(String msg) {
		super(msg);
	}
}