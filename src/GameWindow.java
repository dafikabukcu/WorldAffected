import Countries.*;

import javax.swing.*;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class GameWindow extends JFrame implements KeyListener{

    static int upgrade1Clicked = 0;
    static int upgrade2Clicked = 0;
    static int upgrade3Clicked = 0;


    static boolean ctrlPressed = false;
    static boolean shiftPressed = false;
    static boolean qPressed = false;


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
    public static JButton upgradeButton = new JButton("UPGRADE 1");
    public static JButton upgradeButton2 = new JButton("UPGRADE 2");
    public static JButton upgradeButton3 = new JButton("UPGRADE 3");

    public static JLabel healedLabel = new JLabel("Healed People: "+World.healedPeople);

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

//    public GameWindow(){
//
//    }

    static TimerRunnable timerRunnable = new TimerRunnable();
    public static Thread thread1 = new Thread(timerRunnable);
    static PopulationRunnable runnable = new PopulationRunnable();
    public static Thread thread2 = new Thread(runnable);

    public static boolean isRunning = true;

    public GameWindow(){
        World.initializeCountryMap();

        map.setLayout(new BorderLayout());

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

        // Upgrades Tab
        upgrades.setLayout(null);
        JLabel label = new JLabel("-1000 Score");
        JLabel label2 = new JLabel("-2000 Score");
        JLabel label3 = new JLabel("-5000 Score");
        upgrades.add(label);
        upgrades.add(label2);
        upgrades.add(label3);
        label.setBounds(80, 270, 100, 30);
        label2.setBounds(460, 270, 100, 30);
        label3.setBounds(820, 270, 100, 30);

        upgrades.add(upgradeButton);
        upgrades.add(upgradeButton2);
        upgrades.add(upgradeButton3);
        upgradeButton.setBounds(80, 300, 240, 50);
        upgradeButton2.setBounds(460, 300, 240, 50);
        upgradeButton3.setBounds(820, 300, 240, 50);
        upgradeButton.setEnabled(false);
        upgradeButton2.setEnabled(false);
        upgradeButton3.setEnabled(false);
        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (World w : Virus.infectedCountries) {
                    String countryName = w.getClass().getSimpleName();
                    Field countryField = null;
                    try {
                        countryField = w.getClass().getDeclaredField("virusSpreadSpeed");
                        countryField.setAccessible(true);
                        int cntryInt = countryField.getInt(w.getClass());
                        //System.out.println("ThaiSpeed: "+Thailand.virusSpreadSpeed);
                        countryField.setInt(w, cntryInt * 2);
                    } catch (NoSuchFieldException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                TimerRunnable.score -= 1000;
                upgrade1Clicked = 1;
                upgradeButton2.setEnabled(true);
                upgradeButton.setEnabled(false);
            }
        });
        upgradeButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TimerRunnable.score<2000){
                    upgradeButton2.setEnabled(false);
                    System.out.println("User doesn't have enough score.");
                }else{
                    for (World w : Virus.infectedCountries) {
                        String countryName = w.getClass().getSimpleName();
                        Field countryField = null;
                        try {
                            countryField = w.getClass().getDeclaredField("virusSpreadSpeed");
                            countryField.setAccessible(true);
                            int cntryInt = countryField.getInt(w.getClass());
                            //System.out.println("ThaiSpeed: "+Thailand.virusSpreadSpeed);
                            countryField.setInt(w, cntryInt * 2);
                        } catch (NoSuchFieldException ex) {
                            throw new RuntimeException(ex);
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    TimerRunnable.score -= 2000;
                    upgrade2Clicked = 1;
                    upgradeButton3.setEnabled(true);
                    upgradeButton2.setEnabled(false);
                }
            }
        });
        upgradeButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TimerRunnable.score<5000){
                    upgradeButton2.setEnabled(false);
                    System.out.println("User doesn't have enough score.");
                }else{
                    for (World w : Virus.infectedCountries) {
                        String countryName = w.getClass().getSimpleName();
                        Field countryField = null;
                        try {
                            countryField = w.getClass().getDeclaredField("virusSpreadSpeed");
                            countryField.setAccessible(true);
                            int cntryInt = countryField.getInt(w.getClass());
                            //System.out.println("ThaiSpeed: "+Thailand.virusSpreadSpeed);
                            countryField.setInt(w, cntryInt * 2);
                        } catch (NoSuchFieldException ex) {
                            throw new RuntimeException(ex);
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    TimerRunnable.score -= 5000;
                    upgradeButton3.setEnabled(false);
                }
            }
        });


        //
        Color color = new Color(5,10,16);
        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(color);
        bottomBar.setLayout(new FlowLayout());
        bottomBar.add(healedLabel);
        healedLabel.setSize(500,200);
        healedLabel.setForeground(Color.GREEN);
        map.add(bottomBar, BorderLayout.PAGE_END);




        // Top Bar of Right Panel
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout());
        topBar.setBackground(color);

        //World countries[] = {new China(), new India(), new Thailand(), new Turkey(), new Russia(), new Germany(), new Iceland(), new Italy(), new Poland(), new USA()};
        JComboBox countryList = new JComboBox<>(countries);
        countryList.setOpaque(false);
        countryList.setUI(new MetalComboBoxUI());
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
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                BufferedWriter writer = null;
//                BufferedReader reader = null;
//                try{
//                    Date date = new Date();
//                    String formattedDate = sdf.format(date);
//                    writer = new BufferedWriter(new FileWriter("save.txt", true));
//                    writer.write(formattedDate + ": "+TimerRunnable.score);
//                    writer.newLine();
//                    writer.close();
//
//                }catch (Exception ex){
//                    System.out.println("File not found");
//                }
                //savePlayer("Player", String.valueOf(TimerRunnable.score));
                SwingUtilities.invokeLater(()-> new SaveWindow());

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
        GameWindow.isRunning=false;
        System.exit(0);
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




    public static void savePlayer(String name, String score){
        Player player = new Player(name, score);

        try(FileOutputStream fos = new FileOutputStream("scores.worldAffected")){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(player);
                //oos.write('\n');
            }
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
            System.err.println("File not found.");
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.err.println("Could be an unsupported character.");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_CONTROL: ctrlPressed=true;
            case KeyEvent.VK_SHIFT: shiftPressed=true;
            case KeyEvent.VK_Q: qPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_CONTROL: ctrlPressed=false;
            case KeyEvent.VK_SHIFT: shiftPressed=false;
            case KeyEvent.VK_Q: qPressed=false;
        }
    }
}
