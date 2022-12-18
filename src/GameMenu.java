import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
public class GameMenu{

    public JButton b1;
    public JButton b2;
    public JButton b3;
    public JButton b4;
    public JButton b5;
    public JButton b6;

    public static JPanel selectedPanel = GameWindow.map;

    public GameMenu(){
        //List<String> options = Arrays.asList("MAP", "STATUS", "UPGRADES", "NEWS", "SAVE GAME","EXIT");

        JButton b1 = new JButton("MAP");
        //b1.setPreferredSize(new Dimension(150, 40));
        b1.setMinimumSize(new Dimension(95,30));
        b1.setMaximumSize(new Dimension(95,100));
        this.b1 = b1;

        JButton b2 = new JButton("UPGRADES");
        b2.setMinimumSize(new Dimension(95,30));
        b2.setMaximumSize(new Dimension(95,100));
        this.b2 = b2;

        JButton b3 = new JButton("STATUS");
        b3.setMinimumSize(new Dimension(95,30));
        b3.setMaximumSize(new Dimension(95,100));
        this.b3 = b3;

        JButton b4 = new JButton("NEWS");
        b4.setMinimumSize(new Dimension(95,30));
        b4.setMaximumSize(new Dimension(95,100));
        this.b4 = b4;

        JButton b5 = new JButton("SAVE");
        b5.setMinimumSize(new Dimension(95,30));
        b5.setMaximumSize(new Dimension(95,100));
        this.b5 = b5;

        JButton b6 = new JButton("EXIT");
        b6.setMinimumSize(new Dimension(95,30));
        b6.setMaximumSize(new Dimension(95,100));
        this.b6 = b6;
    }
}
