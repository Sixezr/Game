package ru.kpfu.itis.knives.exceptions;

public class WrongRegionBoundsException extends RuntimeException {
    public WrongRegionBoundsException(String message) {
        super(message);
    }
}
