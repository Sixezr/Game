package ru.kpfu.itis.knives.exceptions;

public class WrongRegionBoundsException extends RuntimeException {
    public WrongRegionBoundsException() {
    }

    public WrongRegionBoundsException(String message) {
        super(message);
    }

    public WrongRegionBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongRegionBoundsException(Throwable cause) {
        super(cause);
    }

    public WrongRegionBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
