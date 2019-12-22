/*public class MapBoundary implements IPositionChangeObserver {
    private SortedSet<Vector2d> byXs;
    private SortedSet<Vector2d> byYs;

    public MapBoundary() {
        byXs = new TreeSet<Vector2d>(new ComparatorByXs());
        byYs = new TreeSet<Vector2d>(new ComparatorByYs());
    }

    public void addMapElement(AbstractWorldObject element) {
        byXs.add(element.getPosition());
        byYs.add(element.getPosition());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        byXs.remove(oldPosition);
        byYs.remove(oldPosition);
        byXs.add(newPosition);
        byYs.add(newPosition);
    }

    public Vector2d getLowerLeft() {
        if (!(byXs.isEmpty() || byYs.isEmpty()))
            return new Vector2d(byXs.first().x, byYs.first().y);
        return new Vector2d(0, 0);
    }

    public Vector2d getUpperRight() {
        if (!(byXs.isEmpty() || byYs.isEmpty()))
            return new Vector2d(byXs.last().x, byYs.last().y);
        return new Vector2d(0, 0);

    }

}*/
