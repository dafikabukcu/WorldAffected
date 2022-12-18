import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveWindow extends JFrame implements ActionListener {

    static JTextField textField;

    public SaveWindow(){
        Color color = new Color(5,10,16);
        JPanel textPanel = new JPanel();
        JButton textSaveButton = new JButton("SAVE");
        textSaveButton.addActionListener(this);

        textField = new JTextField("Your Name", 5);

        textPanel.add(textField);
        textField.setBounds(5, 10, 175, 25);
        textSaveButton.setBackground(color);
        textPanel.add(textSaveButton);
        textSaveButton.setBounds(52, 52, 80, 35);
        textSaveButton.setForeground(Color.RED);
        setIconImage(new ImageIcon("icon.png").getImage());
        setTitle("World Affected");
        setSize(200,160);
        //textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.Y_AXIS));
        textPanel.setLayout(null);
        add(textPanel);
        textPanel.setBackground(color);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SAVE")){
            if (textField.getText().length()>8 || textField.getText().length()<3){
                PopulationRunnable.sendPopUpMsg("You cannot exceed 8 characters, and you should enter minimum of 3.");
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                BufferedWriter writer = null;
                try{
                    Date date = new Date();
                    String formattedDate = sdf.format(date);
                    writer = new BufferedWriter(new FileWriter("save.txt", true));
                    writer.write(textField.getText()+": "+TimerRunnable.score+ " -- ("+formattedDate+")");
                    writer.newLine();
                    writer.close();

                }catch (Exception ex){
                    System.out.println("File not found");
                }
                this.setVisible(false);
            }
        }
    }
}
