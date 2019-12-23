public class World {
    public static void main(String[] args){
        try {
            //WORLD CONST
                //MAP
            Vector2d upperRight = new Vector2d(30,30);
            Vector2d jungleLowerLeft = new Vector2d(10,10);
            Vector2d jungleUpperRight = new Vector2d(20,20);
                //CREATURES
            int startAnimalNumber = 10;
            int startEnegry =100;
            int grassNumber = 2; //Best works with even numbers
                //BASIC PARAMETERS
            int parousiaDay =20000;
                //CREATION
            Boundary boundary = new Boundary(upperRight,jungleLowerLeft,jungleUpperRight);
            Multiversum grassField = new Multiversum(grassNumber, parousiaDay, boundary,
                    startAnimalNumber, startEnegry) {
            };
            AbstractWorldMap w1 = grassField.createNewWorld();
            //AbstractWorldMap w2 = grassField.createNewWorld();
        } catch(IllegalArgumentException | IllegalStateException | InterruptedException ex) {
            System.out.println("There is an interesting message for you.");
            System.out.println(ex);
        }
    }
}
