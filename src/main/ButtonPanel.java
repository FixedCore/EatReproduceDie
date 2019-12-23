import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JButton { //DISPLAYS BUTTONS ON MAIN FRAME
    private AbstractWorldMap field;
    JComboBox judgeAnimal;


    ButtonPanel(AbstractWorldMap field){
        this.field = field;
        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
    //BUTTON CONFIGURATION
        JButton bendTime = new JButton("Bend time");
        JButton getMeanStatistics = new JButton("Get mean statistics");
        JButton createNewGrasfield = new JButton("Crete new grassfield");
        JButton getInformation = new JButton("Judge animal");
        this.judgeAnimal = new JComboBox(); // todo add a list

        bendTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                field.bendTime();
                turnOnJudgment();
            }
        });
        createNewGrasfield.addActionListener(new ActionListener() {//todo
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    field.multiversum.createNewWorld();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        getInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object object =judgeAnimal.getSelectedItem();
                if(object instanceof Animal)
                    new AnimalJudgementPanel((Animal) object);
            }
        });

/*
        judgeAnimal.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getItem() instanceof Animal)
                    new DetailsPanel((Animal) itemEvent.getItem());
            }
        });
*/

/*        int index = carList.getSelectedIndex();
        System.out.println(index);
        Car selectedCar;
        if(index < simulation.map.carList.size()) selectedCar = simulation.map.carList.get(index);
        else selectedCar = simulation.map.deadCarList.get(index-simulation.map.carList.size());
        CarInformationFrame newFrame = new CarInformationFrame(selectedCar);
        */
    //LAYOUT CONFIGURATION
        setLayout(new GridBagLayout());
        setVisible(true);
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        add(bendTime, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(createNewGrasfield);

        gc.gridx = 2;
        gc.gridy = 0;
        add(getMeanStatistics,gc);

        gc.gridx =3;
        add(getInformation, gc);

        gc.gridx = 4;
        add(judgeAnimal,gc);
        judgeAnimal.setVisible(false);
    }

    public void turnOnJudgment(){
        this.judgeAnimal.setVisible(true);
        for(Animal animal : field.getListOfAnimals()) judgeAnimal.addItem(animal);
        for(Animal animal : field.getTheDead()) judgeAnimal.addItem(animal);
    }

}
