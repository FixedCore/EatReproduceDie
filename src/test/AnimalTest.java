/*

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalTest {
    Animal animal;
    @Before
    public void setup(){
        animal = new Animal();
    }
    @Test
    public void boundaries(){
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                animal.move(MoveDirection.FORWARD);
            }
            animal.move(MoveDirection.LEFT);
        }
        Assert.assertTrue("(4,0)".equals(animal.toString()));
    }
    @Test
    public void circle(){
        for(int i = 0; i< 40; i++){
            animal.move(MoveDirection.FORWARD);
            animal.move(MoveDirection.RIGHT);
        }
    Assert.assertTrue("(2,2)".equals(animal.toString()));
    }
    @Test
    public void rotate(){
        for(int i=0; i < 100; i++){
            animal.move(MoveDirection.RIGHT);
        }
        for(int i=0; i < 200; i++){
            animal.move(MoveDirection.LEFT);
        }
    Assert.assertTrue("(2,2)".equals(animal.toString()));
    }
    @Test
    public void parser_test(){
        String[] args = new String[] {"f", "forward" ,"f"};
        OptionsParser parser = new OptionsParser();
        MoveDirection[] animlasMove = parser.parse(args);
        for(MoveDirection i : animlasMove) animal.move(i);
        Assert.assertTrue("(2,4)".equals(animal.toString()));
    }
}

*/
