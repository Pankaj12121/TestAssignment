package com.tieto.frameworkExceptions;
@SuppressWarnings("serial")
public class FrameworkExecutionException  extends RuntimeException{
	
	    public FrameworkExecutionException(String message) {
	        super(message);
	    }

	    public FrameworkExecutionException(String message, Throwable cause) {
	        super(message, cause);
	    }
	}



