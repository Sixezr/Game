package ru.kpfu.itis.knives.exceptions;

public class MessageListenerException extends RuntimeException {
    public MessageListenerException() {
    }

    public MessageListenerException(String message) {
        super(message);
    }

    public MessageListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageListenerException(Throwable cause) {
        super(cause);
    }

    public MessageListenerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
