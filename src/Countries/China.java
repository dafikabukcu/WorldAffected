package Countries;

import java.text.DecimalFormat;

public class China extends Asia{

    //public static long population = 1452687695L;
    //public long population = World.chinaPopulation;
    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;

    public China() {
        super(true,false,true,true,false);
    }

    public double getVirusSpreadSpeed(){
        return virusSpreadSpeed;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.chinaPopulation;
        String formattedNum = format.format(d);
        return "China - Population: "+formattedNum;
    }
}
