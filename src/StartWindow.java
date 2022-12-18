import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class StartWindow extends JFrame{


    public static int starterCounter = 0;

    public static JPanel startPanel = new JPanel(){

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon("bg3.png");
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }

    };

    public static JPanel highScoresPanel = new JPanel(){

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon("bg3.png");
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }

    };

    public StartWindow(){
        //JFrame frame = new JFrame("World Affected");
        //frame.setContentPane(startPanel);
        startPanel.setLayout(null);
        highScoresPanel.setLayout(null);


        //region Images
        try {
            BufferedImage waImage = ImageIO.read(new File("worldaffectedlabel.png"));
            JLabel waLabel = new JLabel(new ImageIcon(waImage));
            add(waLabel);
            waLabel.setBounds(50,-150,500,500);
        } catch (IOException e) {
            System.err.println("You shouldn't have changed the files.");
        }
        //endregion

        //region Buttons
        JButton startButton = new JButton("Start Game");
        try{
            BufferedImage startImage = ImageIO.read(new File("startgame.png"));
            startButton.setIcon(new ImageIcon(startImage));
            startButton.setIconTextGap(-15);
        }catch (IOException e){
            System.err.println("You shouldn't have changed the files.");
        }
        startPanel.add(startButton);
        startButton.setBounds(180,200,240,50);
        startButton.setBackground(Color.black);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionWindow();
            }
        });


        JButton highScoresButton = new JButton("High Scores");
        try{
            BufferedImage hiImage = ImageIO.read(new File("highscores.png"));
            highScoresButton.setIcon(new ImageIcon(hiImage));
            highScoresButton.setIconTextGap(-15);
        }catch (IOException e){
            System.err.println("You shouldn't have changed the files.");
        }
        startPanel.add(highScoresButton);
        //highScoresButton.setBounds(217, 290, 150, 50);
        highScoresButton.setBounds(180, 290, 240, 50);
        highScoresButton.setBackground(Color.black);
        highScoresButton.setOpaque(false);
        highScoresButton.setContentAreaFilled(false);
        highScoresButton.setBorderPainted(false);
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPanel.setVisible(false);
                highScoresPanel();
                add(highScoresPanel);
            }
        });


        JButton leaveGameButton = new JButton("Leave Game");
        try{
            BufferedImage leImage = ImageIO.read(new File("leavegame.png"));
            leaveGameButton.setIcon(new ImageIcon(leImage));
            leaveGameButton.setIconTextGap(-15);
        }catch (IOException e){
            System.err.println("You shouldn't have changed the files.");
        }
        startPanel.add(leaveGameButton);
        leaveGameButton.setBounds(200, 380, 240, 50);
        leaveGameButton.setBackground(Color.black);
        leaveGameButton.setOpaque(false);
        leaveGameButton.setContentAreaFilled(false);
        leaveGameButton.setBorderPainted(false);
        leaveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //endregion




        add(startPanel);
        setIconImage(new ImageIcon("icon.png").getImage());
        setTitle("World Affected");
        setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

    }

    private JPanel highScoresPanel(){

        try {
            BufferedImage waImage = ImageIO.read(new File("worldaffectedlabel.png"));
            JLabel waLabel = new JLabel(new ImageIcon(waImage));
            add(waLabel);
            waLabel.setBounds(50,-150,500,500);
        } catch (IOException e) {
            System.err.println("You shouldn't have changed the files.");
        }
        try{
            BufferedImage hiImage = ImageIO.read(new File("highscores.png"));
            JLabel hiLabel = new JLabel(new ImageIcon(hiImage));
            add(hiLabel);
            hiLabel.setBounds(180, 160, 240, 50);
        }catch (IOException e){
            System.err.println("You shouldn't have changed the files.");
        }
        JButton backButton = new JButton("Back");
        try{
            BufferedImage baImage = ImageIO.read(new File("back.png"));
            backButton.setIcon(new ImageIcon(baImage));
            backButton.setIconTextGap(-15);
        }catch (IOException e){
            System.err.println("You shouldn't have changed the files.");
        }

        BufferedReader reader = null;
        // Get Data And List Them With DefaultListModel
        DefaultListModel defListModel = new DefaultListModel<>();
        JList list = new JList<>(defListModel);
        JScrollPane scrollPane = new JScrollPane(list);
        try{
            reader = new BufferedReader(new FileReader("save.txt"));

            String r;

            while ((r = reader.readLine()) != null){
                defListModel.addElement(r);
            }

            Color color = new Color(5,10,16);
            add(scrollPane);
            scrollPane.setBounds(205, 235, 180, 150);
            list.setBackground(color);
            list.setForeground(Color.WHITE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highScoresPanel.setVisible(false);
                scrollPane.setVisible(false);
                startPanel.setVisible(true);
                readObjects();
            }
        });
        highScoresPanel.add(backButton);
        backButton.setBounds(180, 500, 240, 50);
        backButton.setBackground(Color.black);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);


        highScoresPanel.setVisible(true);
        return highScoresPanel;
    }

    private void optionWindow(){
        this.setVisible(false);
        SwingUtilities.invokeLater(()-> new OptionWindow());
    }

    private void readObjects(){
        try(FileInputStream fis = new FileInputStream("scores.worldAffected")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                Player playerRead = (Player) ois.readObject();
                Player playerRead2 = (Player) ois.readObject();
//                System.out.println(playerRead);
                //System.out.println(playerRead2);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }catch (EOFException e){
                System.out.println("no");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
