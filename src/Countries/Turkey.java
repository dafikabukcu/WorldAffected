package Countries;

import java.text.DecimalFormat;

public class Turkey extends Asia{

    public static int population = 86559096;

    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;


    public Turkey() {
        super(true,true,true,false,false);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.turkeyPopulation;
        String formattedNum = format.format(d);
        return "Turkey - Population: "+formattedNum;
    }
}
