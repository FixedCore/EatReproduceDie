import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JButton { //DISPLAYS BUTTONS ON MAIN FRAME
    private AbstractWorldMap field;


    public ButtonPanel(AbstractWorldMap field){
        this.field = field;
        //BUTTON CONFIGURATION
        JButton bendTime = new JButton("Bend time");
        JButton getMeanStatistics = new JButton("Get mean statistics");
        JButton createNewGrasfield = new JButton("Crete new grassfield");
        //JButton judgeAnimal; // todo add a list

        bendTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                field.bendTime();
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






    }



}
