package com.example.react.exception;

public class ContactException extends RuntimeException {
    public ContactException() {
        super();
    }

    public ContactException(String s) {
        super(s);
    }

    public ContactException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ContactException(Throwable throwable) {
        super(throwable);
    }

    protected ContactException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
