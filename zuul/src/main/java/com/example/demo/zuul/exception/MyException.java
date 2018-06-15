package com.example.demo.zuul.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MyException {

	@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
	public static class RibbonTimeoutException extends RuntimeException {
		private static final long serialVersionUID = 3357160674664241600L;
		public RibbonTimeoutException() {
	        super();
	    }
	    public RibbonTimeoutException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public RibbonTimeoutException(String message) {
	        super(message);
	    }
	    public RibbonTimeoutException(Throwable cause) {
	        super(cause);
	    }
	}
}
