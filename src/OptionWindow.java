import Countries.Virus;
import Countries.World;

import javax.swing.*;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionWindow extends JFrame {

    public static boolean isGameStarted = false;

    public static int virusSpreadSpeed;

    public OptionWindow(){
        Color color = new Color(5,10,16);
        Color color2 = new Color(36, 71, 103);

        JPanel optionPanel = new JPanel();
        //optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.LINE_AXIS));
        optionPanel.setLayout(null);
        optionPanel.setBackground(color);

        JLabel label = new JLabel("selected country");
        optionPanel.add(label);
        label.setBounds(12,5,165,20);

        JComboBox countryList = new JComboBox(GameWindow.countries);
        countryList.setOpaque(false);
        countryList.setUI(new MetalComboBoxUI());
        optionPanel.add(countryList);
        countryList.setBounds(12,25,215,30);
        countryList.setSelectedIndex(0);
        countryList.setBackground(color2);
        countryList.setForeground(Color.WHITE);

        String difficulties[] = {"Easy", "Medium", "Hard"};

        JComboBox difficultySelection = new JComboBox(difficulties);
        difficultySelection.setOpaque(false);
        difficultySelection.setUI(new MetalComboBoxUI());
        difficultySelection.setBackground(color2);
        difficultySelection.setForeground(Color.WHITE);
        optionPanel.add(difficultySelection);
        difficultySelection.setBounds(12,70,215,30);
        difficultySelection.setSelectedIndex(1);

        label.setText(countryList.getSelectedItem().toString());



        JButton button = new JButton("START");
        optionPanel.add(button);
        button.setBounds(55,130,130,25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PopulationRunnable.selectedCountry = countryList.getSelectedItem();
                PopulationRunnable.infect((World) countryList.getSelectedItem());
                switch (difficultySelection.getSelectedIndex()){
                    case 0:
                        //Virus.spreadSpeed = 85000;
                        Virus.spreadSpeed = 15000;
                        Virus.researchCenterChance = 5;
                        break;
                    case 1:
                        //Virus.spreadSpeed = 45000;
                        Virus.spreadSpeed = 35000;
                        Virus.researchCenterChance = 4;
                        break;
                    case 2:
                        //Virus.spreadSpeed = 15000;
                        Virus.spreadSpeed = 65000;
                        Virus.researchCenterChance = 3;
                        break;
                }
                gameWindow();
            }
        });


        add(optionPanel);
        setVisible(true);
        setTitle("World Affected");
        setIconImage(new ImageIcon("icon.png").getImage());
        setSize(250,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void gameWindow(){
        this.setVisible(false);
        SwingUtilities.invokeLater(()-> new GameWindow());
        isGameStarted = true;
    }
}
