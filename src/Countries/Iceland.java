package Countries;

import java.text.DecimalFormat;

public class Iceland extends Europe{

    public static int population = 372295;

    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;


    public Iceland() {
        super(false,false,false,false,true);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.icelandPopulation;
        String formattedNum = format.format(d);
        return "Iceland - Population: "+formattedNum;
    }
}
