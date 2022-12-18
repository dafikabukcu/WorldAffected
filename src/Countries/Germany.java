package Countries;

import java.text.DecimalFormat;

public class Germany extends Europe{

    public static int population = 84427027;

    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;


    public Germany() {
        super(false, false,false,false,false);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.germanyPopulation;
        String formattedNum = format.format(d);
        return "Germany - Population: "+formattedNum;
    }
}
