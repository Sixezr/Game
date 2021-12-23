package ru.kpfu.itis.knives.exceptions;

public class IllegalMessageTypeException extends RuntimeException {
    public IllegalMessageTypeException() {
    }

    public IllegalMessageTypeException(String message) {
        super(message);
    }

    public IllegalMessageTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalMessageTypeException(Throwable cause) {
        super(cause);
    }

    public IllegalMessageTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
