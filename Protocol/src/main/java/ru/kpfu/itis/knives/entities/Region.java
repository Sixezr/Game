package ru.kpfu.itis.knives.entities;

import ru.kpfu.itis.knives.exceptions.WrongRegionBoundsException;

import java.util.*;

public class Region {
    private static final float DELTA = (float) 1e-2;

    private Player owner;
    private List<Bound> bounds;
    private float square;
    private boolean island;
    private float minX;
    private float maxX;

    public Region(Player owner, List<Bound> bounds) throws WrongRegionBoundsException {
        this.owner = owner;
        if (bounds.size() < 2) {
            throw new WrongRegionBoundsException("Неверное количество границ.");
        }

        Bound bound = bounds.get(0);
        bounds.remove(bound);

        this.bounds = connect(bounds, bound);
        calcSquare();
        calcIsland();
        calcXBounds();
    }

    private List<Bound> connect(List<Bound> bounds, Bound start) throws WrongRegionBoundsException {
        List<Bound> result = new ArrayList<>();
        result.add(start);

        Point first = start.getPoints().get(1);
        Point last = start.getPoints().get(0);

        while (bounds.size() > 0) {
            Optional<Bound> optionalBound = findNext(bounds, first);
            if (optionalBound.isPresent()) {
                first.setConnected(true);
                Bound bound = optionalBound.get();
                Point connected = bound.getPoints().get(0).equals(first) ? bound.getPoints().get(0) : bound.getPoints().get(1);
                connected.setConnected(true);
                first = bound.getPoints().get(0).equals(first) ? bound.getPoints().get(1) : bound.getPoints().get(0);
                result.add(bound);
            } else {
                throw new WrongRegionBoundsException("Нет следующей для соединения границы.");
            }
        }

        if (first.equals(last)) {
            first.setConnected(true);
            last.setConnected(true);
        }

        for (Bound bound : result) {
            List<Point> points = bound.getPoints();
            if (!(points.get(0).isConnected() && points.get(1).isConnected())) {
                throw new WrongRegionBoundsException("Не все границы были соединены.");
            }
        }

        return result;
    }

    private Optional<Bound> findNext(List<Bound> bounds, Point point) {
        for (Bound bound : bounds) {
            if (bound.getPoints().contains(point) &&
                    (bound.getPoints().get(0).equals(point) && !bound.getPoints().get(0).isConnected() ||
                            bound.getPoints().get(1).equals(point) && !bound.getPoints().get(1).isConnected())) {
                return Optional.of(bound);
            }
        }
        return Optional.empty();
    }

    public boolean hasPoint(Point point) {
        if (point.getX() <= maxX && point.getX() >= minX) {
            List<Pair> pairs = getPairs(point.getX());
            for (Pair pair : pairs) {
                if (point.getY() <= pair.endY && point.getY() >= pair.startY) return true;
            }
        }
        return false;
    }

    public void calcXBounds() {
        float minX = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        for (Bound bound : bounds) {
            minX = Math.min(bound.getPoints().get(0).getX(), minX);
            maxX = Math.max(bound.getPoints().get(1).getX(), maxX);
        }
        this.minX = minX;
        this.maxX = maxX;
    }

    public void calcIsland() {
        for (Bound bound : bounds) {
            if (!bound.isLine()) island = false;
            return;
        }
        island = true;
    }

    public void calcSquare() {
        float square = 0;
        List<Pair> lastPairs = null;
        for (float x = minX; x <= maxX + DELTA; x += DELTA) {
            List<Pair> thisPairs = getPairs(x);
            if (lastPairs != null) {
                for (Pair pair : thisPairs) {
                    Pair lastPair = getPair(pair, lastPairs);
                    square += DELTA * (lastPair.getSize() + pair.getSize()) / 2f;
                }
            }
            lastPairs = thisPairs;
        }
        this.square = square;
    }

    private Pair getPair(Pair thisPair, List<Pair> lastPairs) {
        Pair result = null;
        float size = Float.MIN_VALUE;
        for (Pair pair : lastPairs) {
            float stepSize = (thisPair.getSize() + pair.getSize() -
                    Math.abs(pair.endY - thisPair.endY) -
                    Math.abs(pair.startY - thisPair.startY)) / 2f;

            if (stepSize >= 0 && stepSize > size) {
                size = stepSize;
                result = pair;
            }
        }
        if (result == null) result = new Pair(0, 0);
        return result;
    }

    private List<Pair> getPairs(float x) {
        List<Pair> result = new ArrayList<>();
        List<Float> listY = new ArrayList<>();
        for (Bound bound : bounds) {
            if (bound.hasX(x)) {
                listY.add(bound.getY(x));
            }
        }
        Collections.sort(listY);
        for (int i = 0; i < listY.size(); i += 2) {
            result.add(new Pair(listY.get(i), listY.get(i + 1)));
        }
        return result;
    }

    public Player isTrueOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public List<Bound> getBounds() {
        return bounds;
    }

    public void setBounds(List<Bound> bounds) {
        this.bounds = bounds;
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public boolean isIsland() {
        return island;
    }

    public void setIsland(boolean island) {
        this.island = island;
    }

    public float getMinX() {
        return minX;
    }

    public void setMinX(float minX) {
        this.minX = minX;
    }

    public float getMaxX() {
        return maxX;
    }

    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region = (Region) o;
        return this.owner == region.owner &&
                Math.abs(this.square - region.square) <= 1e-3 &&
                this.island == region.island &&
                Math.abs(this.minX - region.minX) <= 1e-3 &&
                Math.abs(this.maxX - region.maxX) <= 1e-3 &&
                this.bounds.equals(region.bounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, getBounds(), getSquare(), isIsland(), getMinX(), getMaxX());
    }

    private static class Pair {
        private float startY;
        private float endY;

        public Pair(float startY, float endY) {
            this.startY = startY;
            this.endY = endY;
        }

        public float getSize() {
            return endY - startY;
        }

        public float getStartY() {
            return startY;
        }

        public void setStartY(float startY) {
            this.startY = startY;
        }

        public float getEndY() {
            return endY;
        }

        public void setEndY(float endY) {
            this.endY = endY;
        }
    }
}
