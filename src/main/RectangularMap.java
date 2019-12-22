/*
import java.util.ArrayList;


public class RectangularMap extends AbstractWorldMap {
    private int x;
    private int y;
    private Vector2d upperBoundary;
    private Vector2d lowerBoundary;
    public RectangularMap(int x, int y){
        this.x = x;
        this.y = y;
        upperBoundary = new Vector2d(x,y);
        lowerBoundary = new Vector2d(0,0);
        this.listOfAnimals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.preceeds(upperBoundary) && position.follows(lowerBoundary) && !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position) != null) return true;
        return false;
    }


    @Override
    public Object objectAt(Vector2d position) {
        for(Animal a : listOfAnimals){
            if(a.getPosition().equals(position)) return a;
        }
        return null;
    }


}
*/
