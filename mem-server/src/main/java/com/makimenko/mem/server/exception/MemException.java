package com.makimenko.mem.server.exception;

public class MemException extends RuntimeException {

	private static final long serialVersionUID = -8968520235321217728L;

	public MemException() {
		super();
	}

	public MemException(String s) {
		super(s);
	}

	public MemException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public MemException(Throwable throwable) {
		super(throwable);
	}

}
