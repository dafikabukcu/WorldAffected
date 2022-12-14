package Countries;

import java.text.DecimalFormat;

public class Thailand extends Asia{

    public static int population = 70219693;

    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;


    public Thailand() {
        super(true,false,false,true,false);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.thailandPopulation;
        String formattedNum = format.format(d);
        return "Thailand - Population: "+formattedNum;
    }
}
