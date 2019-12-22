/*
public class GrassFieldTest {
    private Animal animal_2_2, animal_0_0, animal_2_3;
    private Vector2d v8_8,v3_2 ,v2_2, v2_3, v0_0, v1_0;
    private IWorldMap map;
    @Before
    public void setup(){
        map = new GrassField(10);
        v8_8 = new Vector2d(8,8);
        v3_2 = new Vector2d(3,2);
        v2_2 = new Vector2d(2,2);
        v2_3 = new Vector2d(2,3);
        v1_0 = new  Vector2d(1,0);
        v0_0 = new Vector2d(0,0);
        animal_2_2 = new Animal(map, v2_2);
        animal_0_0 = new Animal(map, v0_0);
        animal_2_3 = new Animal(map, v2_3);
    }

    @Test
    public void grassPlacement(){
        GrassField customGrassField = new GrassField(4);
        assertEquals(customGrassField.size(),4);

    }

    @Test
    public void animalOnGrass(){
        Grass grass = new Grass(v2_3);
        map.place(animal_2_2);
        String [] args = {"f"};
        MoveDirection [] directions = OptionsParser.parse(args);
        map.run(directions);
        assertEquals(animal_2_2.getPosition(), v2_3);
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
    public void animalPlacement(){
       map.place(animal_2_3);
       assertTrue(v2_3.equals(animal_2_3.getPosition()));
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
