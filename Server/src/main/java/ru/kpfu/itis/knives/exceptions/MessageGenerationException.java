package ru.kpfu.itis.knives.exceptions;

public class MessageGenerationException extends RuntimeException {

    public MessageGenerationException() {
    }

    public MessageGenerationException(String message) {
        super(message);
    }

    public MessageGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageGenerationException(Throwable cause) {
        super(cause);
    }

    public MessageGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
