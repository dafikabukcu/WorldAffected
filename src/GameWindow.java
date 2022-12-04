import Countries.*;

import javax.swing.*;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    //region Left side menu Constructors
    public static JPanel menuPanel = new JPanel();


    //MAP
    public static JPanel map = new JPanel(){

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon("bg.jpg");
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }

    };

    //UPGRADES
    public static JPanel upgrades = new JPanel(){

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon("bg2.png");
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }

    };

    //STATUS
    public static JPanel status = new JPanel(){

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon("bg2.png");
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }

    };

    public static World countries[] = {
            new China(),
            new India(),
            new Thailand(),
            new Turkey(),
            new Russia(),
            new Germany(),
            new Iceland(),
            new Italy(),
            new Poland(),
            new USA()
    };

    //endregion

    public GameWindow(){

        World.initializeCountryMap();

        TimerRunnable timerRunnable = new TimerRunnable();
        Thread thread1 = new Thread(timerRunnable);
        PopulationRunnable runnable = new PopulationRunnable();
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();


        // Getting buttons
        GameMenu buttons = new GameMenu();


        //region Left Panel
        menuPanel.setBackground(Color.DARK_GRAY);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.add(buttons.b1);
        menuPanel.add(buttons.b2);
        menuPanel.add(buttons.b3);
        menuPanel.add(buttons.b4);
        menuPanel.add(buttons.b5);
        menuPanel.add(buttons.b6);
        //endregion

        //region Right Panel
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, GameMenu.selectedPanel);
        splitPane.setDividerLocation(92);
        splitPane.setOneTouchExpandable(true);

        // Top Bar of Right Panel
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout());

        //World countries[] = {new China(), new India(), new Thailand(), new Turkey(), new Russia(), new Germany(), new Iceland(), new Italy(), new Poland(), new USA()};
        JComboBox countryList = new JComboBox<>(countries);
        countryList.setOpaque(false);
        countryList.setUI(new MetalComboBoxUI());
        Color color = new Color(5,10,16);
        Color color2 = new Color(36, 71, 103);
        countryList.setForeground(Color.white);
        countryList.setBackground(color2);
        countryList.setSelectedIndex(0);
        countryList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        topBar.add(countryList);
        map.add(topBar, BorderLayout.PAGE_START);
        //endregion

        //region Button Actions
        buttons.b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b1");
                GameMenu.selectedPanel = GameWindow.map;
                splitPane.setRightComponent(GameMenu.selectedPanel);
            }
        });
        buttons.b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b2");
                GameMenu.selectedPanel = GameWindow.upgrades;
                splitPane.setRightComponent(GameMenu.selectedPanel);
            }
        });
        buttons.b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b3");
                GameMenu.selectedPanel = GameWindow.status;
                splitPane.setRightComponent(GameMenu.selectedPanel);
            }
        });
        buttons.b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b4");
                GameMenu.selectedPanel = GameWindow.map;
                splitPane.setRightComponent(GameMenu.selectedPanel);
            }
        });
        buttons.b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b5");
                GameMenu.selectedPanel = GameWindow.map;
                splitPane.setRightComponent(GameMenu.selectedPanel);
            }
        });
        buttons.b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b6");
                startWindow();
            }
        });
        //endregion

        // Fundamental Commands
        add(splitPane);
        setIconImage(new ImageIcon("icon.png").getImage());
        setTitle("World Affected");
        setSize(1264,711);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void startWindow(){
        this.setVisible(false);
        SwingUtilities.invokeLater(()-> new StartWindow());
    }

    public int warnUser(){
        String[] options = {"Yes, exit the game!","Cancel, I forgot to save!"};
        int a = JOptionPane.showOptionDialog(
                null,
                "Are you sure you want to exit? All unsaved data will be lost.",
                "WorldAffected",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );

        return a;
    }
}
