package ru.kpfu.itis.knives.entities;

import java.util.*;

public class Bound {
    private final boolean line;
    private final List<Point> points = new ArrayList<>();
    private float k;
    private float b;
    private int quarter;

    public Bound(boolean line, float x1, float y1, float x2, float y2) {
        this(line, new Point(x1, y1), new Point(x2, y2));
    }

    public Bound(boolean line, Point p1, Point p2) {
        this.line = line;
        if (line) {
            points.add(p1);
            points.add(p2);
            Collections.sort(points);
            this.k = (points.get(1).getY() - points.get(0).getY()) / (points.get(1).getX() - points.get(0).getX());
            this.b = points.get(0).getY() - (this.k) * points.get(0).getX();
        } else {
            points.add(p1);
            points.add(p2);
            Collections.sort(points);
            if (points.get(0).getX() < 0f) quarter = points.get(0).getY() >= 0f ? 2 : 3;
            else quarter = points.get(0).getY() >= 0f ? 1 : 4;
        }
    }

    public boolean contains(Point point) {
        return hasX(point.getX()) && hasY(point.getY());
    }

    public Optional<Point> samePoint(Bound bound) {
        for (float x = this.points.get(0).getX(); x <= this.points.get(1).getX(); x += 0.1) {
            if (bound.hasX(x) && Math.abs(this.getY(x) - bound.getY(x)) <= 1e-3) {
                return Optional.of(new Point(x, this.getY(x)));
            }
        }
        return Optional.empty();
    }

    public boolean hasX(float x) {
        return points.get(0).getX() <= x + 1e-3 && x - 1e-3 <= points.get(1).getX();
    }

    public boolean hasY(float y) {
        return hasX(getX(y));
    }

    public float getX(float y) {
        float x = line ? (y - b) / k : (float) Math.sqrt(10000f - y * y);
        if (!line && (quarter == 2 || quarter == 3)) x *= -1;
        return x;
    }

    public float getY(float x) {
        float y = line ? k * x + b : (float) Math.sqrt(10000f - x * x);
        if (!line && (quarter == 3 || quarter == 4)) y *= -1;
        return y;
    }

    public void setK(float k) {
        this.k = k;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public boolean isLine() {
        return line;
    }

    public List<Point> getPoints() {
        return points;
    }

    public float getK() {
        return k;
    }

    public float getB() {
        return b;
    }

    public int getQuarter() {
        return quarter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bound)) return false;
        Bound bound = (Bound) o;
        return this.isLine() == bound.isLine() &&
                this.points.get(0).equals(bound.points.get(0)) &&
                this.points.get(1).equals(bound.points.get(1)) &&
                Math.abs(this.k - bound.k) <= 1e-3 &&
                Math.abs(this.b - bound.b) <= 1e-3 &&
                this.quarter == bound.quarter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLine(), getPoints().get(0), getPoints().get(1), getK(), getB(), getQuarter());
    }

    @Override
    public String toString() {
        return (this.isLine() ?
                "Line y = " + this.getK() + "x + " + this.getB() :
                "Circle in " + this.getQuarter() + " quarter "
        ) + " where x in [" + points.get(0).getX() + "; " + points.get(1).getX() + "];";
    }
}
