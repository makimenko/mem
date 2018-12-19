package com.makimenko.mem.server.exception;

public class MemNoDataFoundException extends MemException {

	private static final long serialVersionUID = -3622081256811423535L;

	public MemNoDataFoundException() {
		super();
	}

	public MemNoDataFoundException(String s) {
		super(s);
	}

	public MemNoDataFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public MemNoDataFoundException(Throwable throwable) {
		super(throwable);
	}

}
