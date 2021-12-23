package ru.kpfu.itis.knives.entities;

import ru.kpfu.itis.knives.exceptions.WrongRegionBoundsException;

import java.util.*;

public class Region {
    private static final float DELTA = (float) 1e-2;

    private boolean owner;
    private List<Bound> bounds;
    private float square;
    private boolean island;
    private float minX;
    private float maxX;

    public Region(boolean owner, List<Bound> bounds) throws WrongRegionBoundsException {
        this.owner = owner;
        if (bounds.size() < 2) {
            throw new WrongRegionBoundsException("Неверное количество границ.");
        }

        Bound bound = bounds.get(0);
        bounds.remove(bound);

        this.bounds = connect(bounds, bound);
        calcAll();
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
                bounds.remove(bound);
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

    public void union(Region region) {
        List<Bound> result = new ArrayList<>();
        for (int i = this.bounds.size() - 1; i >= 0; i--) {
            for (int j = 0; j < region.bounds.size(); j++) {
                if (this.bounds.get(i).isLine() == region.bounds.get(j).isLine() &&
                        this.bounds.get(i).getQuarter() == region.bounds.get(j).getQuarter() &&
                        Math.abs(this.bounds.get(i).getK() - region.bounds.get(j).getK()) <= 1e-3 &&
                        Math.abs(this.bounds.get(i).getB() - region.bounds.get(j).getB()) <= 1e-3 &&
                        (this.bounds.get(i).getPoints().get(0).getX() <= region.getBounds().get(j).getPoints().get(0).getX() &&
                                region.getBounds().get(j).getPoints().get(0).getX() <= this.bounds.get(i).getPoints().get(1).getX() ||
                                region.getBounds().get(j).getPoints().get(0).getX() <= this.bounds.get(i).getPoints().get(0).getX() &&
                                        this.bounds.get(i).getPoints().get(0).getX() <= region.getBounds().get(j).getPoints().get(1).getX())) {

                    if (this.bounds.get(i).getPoints().get(0).getX() <= region.getBounds().get(j).getPoints().get(0).getX() &&
                            this.bounds.get(i).getPoints().get(1).getX() >= region.getBounds().get(j).getPoints().get(1).getX()) {
                        result.add(region.getBounds().get(j));
                    } else {
                        result.add(this.bounds.get(i));
                    }
                    region.getBounds().remove(region.getBounds().get(j));
                    this.bounds.remove(this.bounds.get(i));
                    break;
                }
            }
        }

        this.bounds = result;
        calcAll();
    }

    public List<Region> divide(Bound bound) {
        List<Region> result = new ArrayList<>();
        List<Bound> first = new ArrayList<>();
        List<Bound> second = new ArrayList<>();

        List<Point> points = new ArrayList<>();

        List<Bound> commons = new ArrayList<>();

        for (Bound b : this.bounds) {
            if (b.samePoint(bound).isPresent()) {
                points.add(b.samePoint(bound).get());
            }
        }

        points.sort((o1, o2) ->
                (int) (Math.sqrt((o1.getX() - o2.getX()) * (o1.getX() - o2.getX()) +
                        (o1.getY() - o2.getY()) * (o1.getY() - o2.getY())) * 1e3));

        Point firstPoint = points.get(0);
        Point secondPoint = null;

        for (Point p : bound.getPoints()) {
            if (this.hasPoint(p)) {
                for (int i = 1; i < points.size(); i++) {
                    if (p.getX() >= firstPoint.getX() && points.get(i).getX() >= p.getX() ||
                            p.getX() <= firstPoint.getX() && points.get(i).getX() <= p.getX()) {
                        secondPoint = points.get(i);
                        break;
                    }
                }
                break;
            }
        }

        for (Bound b : this.bounds) {
            if (b.contains(firstPoint) || b.contains(Objects.requireNonNull(secondPoint))) {
                commons.add(b);
            }
        }

        bound = new Bound(true, firstPoint, secondPoint);

        first.add(bound);
        second.add(bound);

        List<Bound> bounds = this.bounds;
        if (commons.size() < 4) {
            for (int i = commons.size() - 1; i >= 0; i--) {
                bounds.remove(commons.get(i));
                if (!commons.get(i).getPoints().contains(firstPoint) && !commons.get(i).getPoints().contains(secondPoint)) {
                    if (commons.get(i).contains(firstPoint)) {
                        commons.add(new Bound(commons.get(i).isLine(), commons.get(i).getPoints().get(0), firstPoint));
                        commons.add(new Bound(commons.get(i).isLine(), commons.get(i).getPoints().get(1), firstPoint));
                    } else {
                        commons.add(new Bound(commons.get(i).isLine(), commons.get(i).getPoints().get(0), secondPoint));
                        commons.add(new Bound(commons.get(i).isLine(), commons.get(i).getPoints().get(1), secondPoint));
                    }
                    commons.remove(i);
                }
            }
            bounds.addAll(commons);
        }

        while (!firstPoint.equals(secondPoint) && findNextWithoutConnection(bounds, firstPoint).isPresent()) {
            Bound toAdd = findNextWithoutConnection(bounds, firstPoint).get();
            firstPoint = toAdd.getPoints().get(0).equals(firstPoint) ?
                    toAdd.getPoints().get(1) :
                    toAdd.getPoints().get(0);

            first.add(toAdd);
            bounds.remove(toAdd);
        }
        second.addAll(bounds);

        try {
            result.add(new Region(this.owner, first));
            result.add(new Region(this.owner, second));
        } catch (WrongRegionBoundsException e) {
            // TODO: обработать исключение
        }

        return result;
    }

    private Optional<Bound> findNextWithoutConnection(List<Bound> bounds, Point point) {
        for (Bound bound : bounds) {
            if (bound.getPoints().contains(point)) {
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

    public void calcAll() {
        calcXBounds();
        calcSquare();
        calcIsland();
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
        if (result == null) result = new Pair(thisPair.getSize() / 2, thisPair.getSize() / 2);
        return result;
    }

    private List<Pair> getPairs(float x) {
        List<Pair> result = new ArrayList<>();
        List<Float> listY = new ArrayList<>();
        for (Bound bound : bounds) {
            if (bound.hasX(x) &&
                    (!bound.isLine()  ||
                            (Math.abs(bound.getK() - Float.MAX_VALUE) > 1e-3 &&
                                    Math.abs(bound.getK() - Float.MIN_VALUE) > 1e-3))) {
                listY.add(bound.getY(x));
            }
        }
        Collections.sort(listY);
        for (int i = 0; i < listY.size(); i += 2) {
            result.add(new Pair(listY.get(i), listY.get(i + 1)));
        }
        return result;
    }

    public boolean isTrueOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
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
