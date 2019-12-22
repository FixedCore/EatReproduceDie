import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    boolean isRunning = true;
    private MainFrame Layout = new MainFrame(this);
    private List<Animal> listOfAnimals = new ArrayList<>();
    private List<Animal> theDead =new ArrayList<>();
    private int lifespan = 0;
    private Multimap<Vector2d, AbstractWorldObject> map =  ArrayListMultimap.create();
    private MapVisualizer mapVisualizer;
    private int dayCounter = 0;
    protected Boundary boundary;
    private int grassNumber;
    private StatisticalData statisticalData;
    public Multiversum multiversum;

    public AbstractWorldMap(int grassNumber, Boundary boundary, Multiversum multiversum){
        this.grassNumber = grassNumber;
        this.boundary = boundary;
        this.multiversum = multiversum;
        //this.mapVisualizer = new MapVisualizer(this);
        mapVisualizer = new MapVisualizer(this);
        statisticalData = new StatisticalData(this);

        placeGrass();
        initialAnimalPlacement(multiversum.getStartAnimalNumber());

    }

    public void bendTime(){
        isRunning = !isRunning;
    }

    public void theBeginOfTime() throws InterruptedException {
        for(int i = 0; i<multiversum.getParousiaDay(); i++) {
            dayCounter++;
            if (isRunning) day();
            else while (!isRunning) Thread.sleep(200);
            Thread.sleep(1000);
        }
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        if(oldPosition != null){
            newPosition = boundary.keepInsideBoundaries(newPosition);
            map.remove(oldPosition, animal);
            map.put(newPosition, animal);
        }
        else map.put(newPosition,animal);
    }

    public boolean place(AbstractWorldObject object) throws IllegalArgumentException {
        if(canMoveTo(object.getPosition())) {
            map.put(object.getPosition(), object);
            if(object instanceof Animal) {
                listOfAnimals.add((Animal) object);
                ((Animal) object).addObserver(this);
            }
            return true;
        }
        else {
            throw new IllegalArgumentException("trying to place object at: " + object.getPosition() + ". Position is already occupied by an animal.");
        }

    }


    void day(){
        movementTime();
        eatingTime();
        breedingTime();
        decayCorpses();
        placeGrass();
        String string = this.toString();
        try {
            Layout.changeText(string);
            Layout.actualiseStatistics(statisticalData.toString());
        } catch (Exception ignored){

        }
        //System.out.println(this.toString());
    }

    private void decayCorpses() {
        int totalLifespanToday = 0;
        List<Animal> todayLeaveUs = new ArrayList<>();
        for(Animal animal:listOfAnimals) {
            if(animal.getEnergy() < 0){
                map.remove(animal.getPosition(), animal);
                todayLeaveUs.add(animal);
                totalLifespanToday += animal.getAge();
            }
        }
        this.lifespan = this.lifespan*theDead.size() + totalLifespanToday;
        theDead.addAll(todayLeaveUs);
        if(theDead.size() != 0)this.lifespan /= theDead.size();
        listOfAnimals.removeIf(animal -> animal.getEnergy() < 0 );

       if(listOfAnimals.isEmpty()) throw new IllegalStateException("All animals became extinct before your arrival Lord.");
    }
    void initialAnimalPlacement(int startAnimalNumber) {
        for(int i = 0; i < startAnimalNumber; i++) {
            Vector2d tmp = boundary.randomPosition();
            Animal animal = new Animal(tmp, multiversum.getStartEnergy(), null, null);
            place(animal);
        }
    }
    void placeGrass(){//JUNGLE AND SAVANNA TODO CODE CLEAN UP!
        //IN SAVANNA
        for(int i = 0; i < grassNumber/2; i++) {
            Vector2d tmp = boundary.randomPositionSavanna();
            Optional<Grass> grassOnPosition = getGrass(tmp);
            if(grassOnPosition.isEmpty()) {
                Grass grass = new Grass(tmp);
                place(grass);
                grassNumber++;
            }
            else grassOnPosition.get().increaseEnergy(6);
        }
        //IN JUNGLE
        for(int i = 0; i < grassNumber/2; i++){
            Vector2d tmp = boundary.randomPositionJungle();
            if(!map.containsKey(tmp)) {
                Grass grass = new Grass(tmp);
                map.put(tmp, grass);
                grassNumber++;
            }
        }
    }



    private void movementTime() {
        for (Animal animal : listOfAnimals) {
            animal.move();
        }
    }
    private void eatingTime(){ //todo check if grass is always eaten.
        Set<Vector2d> occupiedPositions = map.keySet();
        for(Vector2d position : occupiedPositions){
            List<Animal> whoEats = new ArrayList<>();
            Collection<AbstractWorldObject> hereAre = map.get(position);
            Grass grassHere = null;
            if(hereAre.size()>1){
                for(AbstractWorldObject object : hereAre){
                    if(object instanceof Animal) {
                        if (((Animal) object).sameEnergy(theStrongest(object.getPosition())))
                            whoEats.add((Animal) object);
                    }
                    else if (object instanceof Grass) grassHere = (Grass) object;
                }
                if(grassHere == null) return;
                int energy = grassHere.getEnergy();
                if(!whoEats.isEmpty()){
                    grassNumber--;
                    energy /= whoEats.size();
                    int finalEnergy = energy; //JAVA makes me to do so ;(
                    whoEats.forEach(animal -> animal.eat(finalEnergy));
                    //AbstractWorldObject grass = hereAre.stream().filter(object -> object instanceof Grass).findAny().get();
                    this.map.remove(position, grassHere);
                    //map.remove(grass.getPosition(), grass);
                }
                //Functional code
                //STREAM TO ARRAY??
                /*whoEats = hereAre.stream().filter(object -> object instanceof Animal).filter(animal -> ((Animal) animal).sameEnergy(theStrongest(animal.getPosition())))
                        .map(Animal.class::cast).toArray();*/
                    /*             Optional<Grass> tmp = hereAre.stream().
                        filter(object -> object instanceof Grass).map(Grass.class::cast).findAny();*/
            }
        }

    }


//BREEDING
    private void breedingTime() {
        HashMap<Vector2d, Animal> whoBreeds = new HashMap<>();
        for(Animal animal: listOfAnimals)
            if(isPartnerHere(animal.getPosition()) && animal.sameEnergy(theStrongest(animal.getPosition())))
                whoBreeds.put(animal.getPosition(), animal);
        if(!whoBreeds.isEmpty())
            whoBreeds.forEach((position, animal) -> animal.mate(findMatingPartner(position)));
    }
    private Animal findMatingPartner(Vector2d position) {
        Collection<AbstractWorldObject> hereAre = map.get(position);
        Animal strongest = theStrongest(position);
        hereAre.remove(strongest);
        Optional<AbstractWorldObject> matingPartner = hereAre.stream()
                .filter(object ->  object instanceof Animal).
                min(comparator);
                    //    min((comparator)); //todo
        return (Animal) matingPartner.orElse(null);
    }
    private boolean isPartnerHere(Vector2d position){
        Collection<AbstractWorldObject> hereAre = map.get(position);
        return hereAre.stream().filter(object -> object instanceof Animal).count() > 1;
    }
    private Comparator<AbstractWorldObject> comparator = Comparator.comparing(o ->
                 ((Animal) o).getEnergy()
    ); //WHY it works?

//USEFUL METHODS
    public Animal theStrongest(Vector2d position){
        Collection<AbstractWorldObject> positionList = map.get(position);
        Animal strongest = (Animal) positionList.stream().filter(object -> object instanceof Animal).findAny().get();//Can I do I better? 2todo
        for(AbstractWorldObject worldObject : positionList) {
            if(worldObject instanceof  Animal){
                if(((Animal) worldObject).getEnergy() >  strongest.getEnergy())
                    strongest = (Animal) worldObject;
            }
        }
        return  strongest;

    }
    private Optional<Grass> getGrass(Vector2d tmp) {
        return map.get(tmp).stream().filter(object -> object instanceof Grass).map(Grass.class::cast).findAny();
    }

    public Object objectAt(Vector2d position) {
        return map.get(position);
    }


    public boolean canMoveTo(Vector2d position) {
        return true;

    }
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public String toString() {
        System.out.println(dayCounter);
        return mapVisualizer.draw(new Vector2d(0,0), boundary.getUpperRight());
    }

//GETTERS
    public int getSize(){
        return map.size();
    }

    private boolean isInList(Grass grass){
        return isOccupied(grass.getPosition());
    }

    public List<Animal> getListOfAnimals() {
        return listOfAnimals;
    }


    public List<Animal> getTheDead() {
        return theDead;
    }

    public int getGrassNumber() {
        return grassNumber;
    }

    public int getLifespan() {
        return lifespan;
    }

    public int getDayCounter() {
        return dayCounter;
    }
}
