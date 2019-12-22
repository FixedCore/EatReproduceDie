import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JLabel label2 = new JLabel();
    DetalisPanel detalisPanel = new DetalisPanel()


    public MainFrame() {
        Font font = new Font(Font.MONOSPACED,Font.PLAIN ,12);
        label2.setFont(font);
        add(label2, BorderLayout.WEST);
        JButton bendTime = new JButton("Bend time");
        JButton getMeanStatistics = new JButton("Get mean statistics");
        JButton createNewGrasfield = new JButton("Crete new grassfield");
        JButton judgeAnimal; // todo add a list
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(1000,1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(bendTime, BorderLayout.NORTH);
    }



    public void changeText(String text) throws InterruptedException {
        text = "<html>" + text.replaceAll("<","&lt;").
                replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
        label2.setText(text);
        Thread.sleep(1000);
    }

}
