package ru.kpfu.itis.knives.helpers;

public enum KnifeState {
    success("Попал!"),
    failure("Не попал!");

    public final String label;

    KnifeState(String label) {
        this.label = label;
    }
}
