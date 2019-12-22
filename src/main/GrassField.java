public class GrassField extends AbstractWorldMap {
    boolean isRunning = true;
    public GrassField(int grassNumber, int parousiaDay, Boundary boundary, int startAnimalNumber, int startEnergy) {
        super(grassNumber, parousiaDay, boundary, startAnimalNumber, startEnergy);
    }
    public void stop(){
        isRunning = false;
    }

    public void start(){
        isRunning = true;
    }

    public void simulate(){
        theBeginOfTime();
    }


}
