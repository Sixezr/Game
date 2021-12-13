package ru.kpfu.itis.knives.entities;

import java.util.Objects;

public class Point implements Comparable<Point> {
    private float x;
    private float y;
    private boolean connected;

    public Point(float x, float y) {
        this(x, y, false);
    }

    public Point(float x, float y, boolean connected) {
        this.x = x;
        this.y = y;
        this.connected = connected;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public int compareTo(Point p) {
        if (this.x - p.x > 0) return 1;
        else if (this.x == p.x) return 0;
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Math.abs(this.getX() - point.getX()) <= 1e-3 && Math.abs(this.getY() - point.getY()) <= 1e-3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
    }

    @Override
    public String toString() {
        return "Point (" + this.getX() + "; " + this.getY() + ") is " + (this.connected ? "" : "not ") + "connected.";
    }
}
