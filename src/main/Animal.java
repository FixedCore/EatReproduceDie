import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class Animal extends AbstractWorldObject {
    private MapDirection dir;
    private Set<IPositionChangeObserver> observerSet;
    private  int energy;
    private ArrayList<Animal> offspring = new ArrayList<>();
    private  Genome genome;
    private  int age;
    private  int birthDate;
    private  AbstractWorldMap map;

    Animal(Vector2d initialPosition, int energy, Animal parent1, Animal parent2, AbstractWorldMap map){
        super(initialPosition);
        this.dir = MapDirection.randomDirection();
        this.observerSet = new HashSet<>();
        this.energy = energy;
        this.genome = new Genome(parent1, parent2);
        this.age = 0;
        this.map = map;
        this.birthDate = map.getDayCounter();
    }



    void mate(Animal matingPartner){
        if(this == matingPartner) return; //todo brzydki kod
        if(this.getAge() < 5 && matingPartner.getAge() < 5) return;
        //if(this.getEnergy()< || matingPartner.getEnergy()< ) //todo stat energy
        //childrenNumber++;
        int childEnergy = (int) ((this.getEnergy() + matingPartner.getEnergy())/4);//why cast to int?? todo
        Animal child = new Animal(this.position, childEnergy, this, matingPartner, map);
        child.positionChanged(null, child.getPosition());
        this.afterMatingExhaustion(); this.rememberOffspring(child);
        matingPartner.afterMatingExhaustion(); matingPartner.rememberOffspring(child);
    }
    private void rememberOffspring(Animal child) {
        this.offspring.add(child);
    }
    private void afterMatingExhaustion(){
        this.energy = (this.energy*3)/4;
    }


    public String toString(){
        int energy = (int) this.energy;
        return String.valueOf(energy);
    }

    public void move () {
        energy--;
        age++;
        this.randomRotate();
        Vector2d oldPosition = position;
        position = position.add(this.dir.toUnitVector());
        position = map.boundary.keepInsideBoundaries(position);
        positionChanged(oldPosition,position);
    }

    void randomRotate(){
        this.dir = MapDirection.rotate(this.dir, MapDirection.values()[genome.getRandomAllele()]);//dzwnie losuje //todo
    }

    public void eat(int energy){
        this.energy += energy;

    }

    void addObserver(IPositionChangeObserver observer){
        observerSet.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        observerSet.remove(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observerSet)
            observer.positionChanged(oldPosition, newPosition, this);

        }

    public boolean sameEnergy(Animal competitor){
        return (java.lang.Math.abs(this.getEnergy() - competitor.getEnergy()) < 0.1);
    }
//STATISTICAL INFORMATION
    int countChildrenTillDay(int day){
        int counter = 0;
        for (Animal offspring : this.getOffspring()) {
            if(offspring.getBirthDate() >= day) counter++;
        }
        return counter;
    }

    int countOffspringTillDay(int day){
        HashSet<Animal> counted = new HashSet<>();
        countOffspringInnerFunction(this, counted, day);
        return counted.size();
    }
    private void countOffspringInnerFunction(Animal animal, HashSet<Animal> counted, int day){
        for(Animal offspring : animal.getOffspring()){
            if(offspring.getBirthDate() >=  day) {
                counted.add(offspring);
                countOffspringInnerFunction(offspring, counted, day);
            }
        }
    }

 //GETTERS
    public int getEnergy() {
        return energy;
    }

    public Genome getGenome() {
        return genome;
    }

    public ArrayList<Animal> getOffspring() {
        return offspring;
    }

    public int getAge() {
        return age;
    }

    public int getChildrenNumber() {
        return offspring.size();
    }

    public int getDeathDay(){
        if(age+birthDate == map.getDayCounter()) return -1;
        return age+birthDate;
    }

    public int getCurrentYear() {
        return age+birthDate;
    }

    public int getBirthDate() {
        return birthDate;
    }
}

