package Countries;

import java.text.DecimalFormat;

public class Poland extends Europe{

    public static int population = 37748161;

    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;


    public Poland() {
        super(false,false,false,false,true);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        Long d = World.polandPopulation;
        String formattedNum = format.format(d);
        return "Poland - Population: "+formattedNum;
    }
}
