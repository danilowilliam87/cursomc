package com.cursomc.service.exception;

public class MethodArgumentException extends RuntimeException{
    private static final long serialVersionUID = 1L;



    public MethodArgumentException(String message) {
        super(message);
    }

    public MethodArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
