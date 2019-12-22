import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest {
    Vector2d v1_1 = new Vector2d(1,1);
    Vector2d v2_1 = new Vector2d(2, 1);
    @Test
    public void isEquals(){
        Assert.assertTrue((v1_1.equals(v1_1)));
    }
    @Test
    public void isEquals2(){
        Assert.assertTrue((v1_1.equals(new Vector2d(1,1))));
    }
    @Test
    public void notEquals(){
        Assert.assertFalse(v2_1.equals(v1_1));
}
    @Test
    public void testToString() {
        Assert.assertEquals(v2_1.toString(), "(2,1)");
    }

    @Test
    public void testPrecedes() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2, 3);
        Assert.assertTrue(v1.preceeds(v2));
        Assert.assertFalse(v2.preceeds(v1));
    }

    @Test
    public void testFollows() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Assert.assertTrue(v2.follows(v1));
        Assert.assertFalse(v1.follows(v2));

    }

    @Test
    public void testUpperRight() {
        Vector2d v1 = new Vector2d(3, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Assert.assertEquals(v1.upperRight(v2), new Vector2d(3, 3));
    }

    @Test
    public void testLowerLeft() {
        Vector2d v1 = new Vector2d(3, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Assert.assertEquals(v1.lowerLeft(v2), new Vector2d(2, 2));
    }

    @Test
    public void testSubtract() {
        Vector2d v1 = new Vector2d(3, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Assert.assertEquals(v1.substract(v2), new Vector2d(1, -1));
    }
    @Test
    public  void testAdd(){
        Vector2d v1 = new Vector2d(1,4);
        Vector2d v2 = new Vector2d(3,1);
        Assert.assertEquals(v1.add(v2), new Vector2d(4,5));
    }

    @Test
    public void testOpposite() {
        Vector2d v = new Vector2d(3, 2);
        Assert.assertEquals(v.oposite(), new Vector2d(-3, -2));
    }
}
