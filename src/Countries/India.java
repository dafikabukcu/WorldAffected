package Countries;

import java.text.DecimalFormat;

public class India extends Asia{

    public static long population = 1413116698L;

    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;

    public India() {
        super(true,true,true,true,false);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.indiaPopulation;
        String formattedNum = format.format(d);
        return "India - Population: "+formattedNum;
    }
}
