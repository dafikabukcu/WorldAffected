import Countries.Virus;
import Countries.World;

public class TimerRunnable implements Runnable{

    static int day = 0;

    public static int score = 0;

    @Override
    public void run() {
        while (GameWindow.isRunning){
            try{
                Thread.sleep(1000);
                System.out.println(Virus.spreadSpeed);
                day++;
                GameWindow.healedLabel.setText("Healed People: "+World.healedPeople+"\t");
                for (int i=0; i<Virus.researchCenters.size(); i++){
                    score+=10;
                }
                if (Virus.researchCenters.size()>4){
                    score+=200;
                }
                if (score>1000)
                    GameWindow.upgradeButton.setEnabled(true);
                if (score>2000 && GameWindow.upgrade1Clicked==1)
                    GameWindow.upgradeButton2.setEnabled(true);
                if (score>5000 && GameWindow.upgrade2Clicked==1)
                    GameWindow.upgradeButton3.setEnabled(true);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Day: "+day+"\nScore: "+score+ "\n------------------------------------------");
        }


    }
}
