public class World {
    public static void main(String[] args){
        try {
            MainFrame gui = new MainFrame();
            //WORLD CONST
                //MAP
            Vector2d upperRight = new Vector2d(30,30);
            Vector2d jungleLowerLeft = new Vector2d(10,10);
            Vector2d jungleUpperRight = new Vector2d(20,20);
                //CREATURES
            int startAnimalNumber = 50;
            int startEnegry = 400;
            int grassNumber = 2;
                //BASIC PARAMETERS
            int parousiaDay =600;
                //CREATION
            Boundary boundary = new Boundary(upperRight,jungleLowerLeft,jungleUpperRight);
            GrassField grassField = new GrassField(grassNumber, parousiaDay, boundary,
            startAnimalNumber, startEnegry);
            grassField.theBeginOfTime();
            //MainFrame mainFrame = new MainFrame();
            //System.out.println(grassField.toString());/
        } catch(IllegalArgumentException | IllegalStateException ex) {
            System.out.println("There is an interesting message for you.");
            System.out.println(ex);
        }
    }
}