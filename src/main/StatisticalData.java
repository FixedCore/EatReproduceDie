import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class StatisticalData {
    private int animalNumber;
    private int grassNumber;
    private int meanEnergy;
    private int meanLifespan = 0; //todo make sure 0 only at start
    private int meanChildrenNumber = 0;
    private AbstractWorldMap map;

    StatisticalData(AbstractWorldMap map){
        this.map = map;

    }

//DAILY STATISTICAL DATA
    void actualiseStatistics(){
        countGrass();
        countAnimals();
        meanEnergyAndMeanChildren();
        meanLifespan();

    }

    String dominatingGene(){
        Map<String, Integer> genes = new HashMap<>();
        for (Animal animal : map.getListOfAnimals()) {
            String string = animal.getGenome().toString();
            if(!genes.containsKey(string))
                genes.put(string, 1);
            else
                genes.put(string, 1 + genes.remove(string));
        }
        Integer max = 0;
        String dominatingGene = null;
        for(String string : genes.keySet()){
            if(genes.get(string) > max){
                max = genes.get(string);
                dominatingGene = string;
            }
        }
        return  dominatingGene;
    }

    void meanEnergyAndMeanChildren(){
        int meanEnergy = 0;
        int meanChildrenNumber = 0;
        for (Animal animal : map.getListOfAnimals()) {
            meanEnergy += animal.getEnergy();
            meanChildrenNumber += animal.getChildrenNumber();
        }
        this.meanEnergy = meanEnergy / map.getListOfAnimals().size();
        this.meanChildrenNumber = meanChildrenNumber / map.getListOfAnimals().size();
    }
    void meanLifespan(){
        this.meanLifespan = map.getLifespan();
    }

    void countAnimals(){
        animalNumber = map.getListOfAnimals().size();
    }

    void countGrass(){
       grassNumber = map.getGrassNumber();
    }

    public String toString(){
        actualiseStatistics();
        return String.format("Total_animal:%d " ,animalNumber).concat(String.format("Total_grass:%d ", grassNumber)).
                concat(String.format("Dominating_gene:%s ", dominatingGene())).concat(String.format("Mean_energy:%d ", meanEnergy)).
                concat(String.format("Mean_lifespan:%d ", meanLifespan)).concat(String.format("Mean_childern_number:%d ",meanChildrenNumber));
    }
//ADVANCED STATISTICAL DATA
    public String showAllDominatingGenomes(){
        Multimap<Vector2d, Animal> dominatorsOccupy =  ArrayListMultimap.create();
        for (Animal animal : map.getListOfAnimals()) {
            if(animal.getGenome().toString().equals(dominatingGene()))
                dominatorsOccupy.put(animal.getPosition(),animal);
        }
        MapVisualizer visualizer = new MapVisualizer(dominatorsOccupy);
        return visualizer.draw(new Vector2d(0,0), map.boundary.getUpperRight());
    }


    int countAllAnimals(){
        int a =1;
        return a;
    }


    public void exportStatisticalData(){
    try {
        File statisticalData = new File(String.format("StatisticalDataAfter%d", map.getDayCounter()));
        if(statisticalData.exists()) return;
        statisticalData.createNewFile();
        PrintWriter pw = new PrintWriter(statisticalData);
        //todo calculate means


        pw.close();
        /*PrintStream statisticalData = System.out;
        PrintStream fileOut = new PrintStream("./out.txt");
        System.setOut(fileOut);
        statisticalData.println("staty");*/
    } catch (IOException ex){
        ex.printStackTrace();
    }
    }


}
