package com.makimenko.mem.server.exception;

public class MemStorgeFileNotFoundException extends MemException {

	private static final long serialVersionUID = -3622081256811423535L;

	public MemStorgeFileNotFoundException() {
		super();
	}

	public MemStorgeFileNotFoundException(String s) {
		super(s);
	}

	public MemStorgeFileNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public MemStorgeFileNotFoundException(Throwable throwable) {
		super(throwable);
	}

}
