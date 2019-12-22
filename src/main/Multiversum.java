public class Multiversum {
    final int startEnergy;
    final int dailyGrassNumber;
    final int parousiaDay;
    final int startAnimalNumber;
    Boundary boundary;

    public Multiversum(int grassNumber, int parousiaDay, Boundary boundary,
                       int startAnimalNumber, int startEnergy){
        this.startEnergy = startEnergy;
        this.dailyGrassNumber =grassNumber;
        this.parousiaDay = parousiaDay;
        this.boundary = boundary;
        this.startAnimalNumber = startAnimalNumber;
    }


    public void createNewWorld() throws InterruptedException {
        AbstractWorldMap world = new AbstractWorldMap(dailyGrassNumber, boundary, this);
        world.theBeginOfTime();
    }

    public int getDailyGrassNumber() {
        return dailyGrassNumber;
    }

    public int getParousiaDay() {
        return parousiaDay;
    }

    public int getStartAnimalNumber() {
        return startAnimalNumber;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public int getStartEnergy(){
        return startEnergy;
    }
}
