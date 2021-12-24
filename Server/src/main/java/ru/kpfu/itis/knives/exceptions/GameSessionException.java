package ru.kpfu.itis.knives.exceptions;

public class GameSessionException extends RuntimeException {
    public GameSessionException() {
    }

    public GameSessionException(String message) {
        super(message);
    }

    public GameSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameSessionException(Throwable cause) {
        super(cause);
    }

    public GameSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
