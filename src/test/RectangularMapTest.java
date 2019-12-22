/*
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangularMapTest {
    private Animal animalOutOfRange, animal_2_2, animal_0_0, animal_2_3;
    private Vector2d v8_8, v3_2, v1_0, v2_3,v2_2, v0_0;
    private IWorldMap map;
    @Before
    public void setup(){
        map = new RectangularMap(5, 5);
        v8_8 = new Vector2d(8,8);
        v2_2 = new Vector2d(2,2);
        v2_3 = new Vector2d(2,3);
        v0_0 = new Vector2d(0,0);
        v3_2 = new Vector2d(3,2);
        v1_0 = new Vector2d(1,0);
        animalOutOfRange = new Animal(map, v8_8);
        animal_2_2 = new Animal(map, v2_2);
        animal_0_0 = new Animal(map, v0_0);
        animal_2_3 = new Animal(map, v2_3);
    }


    //Obsługa przez wyjatek

    @Test(expected = IllegalArgumentException.class)
    public void placementBoundaries(){
        assertFalse(map.place(animalOutOfRange));
    }



  @Test(expected = IllegalArgumentException.class)
    public void doublePlacement(){
        map.place(animal_2_2);
        assertFalse(map.place(animal_2_2));
    }


    @Test
    public void testObjectAt(){
        map.place(animal_2_2);
        Vector2d vector = new Vector2d(2,2);
        assertEquals(animal_2_2, (map.objectAt(vector)));
    }

    @Test
    public void animalCollision(){
        String [] args = {"f"};
        MoveDirection [] directions = OptionsParser.parse(args);
        map.place(animal_2_2);
        map.place(animal_2_3);
        map.run(directions);
        assertEquals(v2_2, animal_2_2.getPosition());
        assertEquals(v2_3, animal_2_3.getPosition());
    }

    @Test
    public void movingAround(){
        String [] args = {"f", "f", "r", "r", "f", "f", "r", "r", "f", "f"};
        MoveDirection [] directions = OptionsParser.parse(args);
        map.place(animal_2_2);
        map.place(animal_0_0);
        map.run(directions);
        Assert.assertEquals(v3_2, animal_2_2.getPosition());
        Assert.assertEquals(v1_0, animal_0_0.getPosition());
    }
}
*/
