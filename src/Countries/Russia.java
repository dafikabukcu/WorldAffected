package Countries;

import java.text.DecimalFormat;

public class Russia extends Asia{

    public static long population = 146083645L;

    public static int virusSpreadSpeed = Virus.spreadSpeed;


    public Russia() {
        super(false,true,false,false,true);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.russiaPopulation;
        String formattedNum = format.format(d);
        return "Russia - Population: "+formattedNum;
    }
}
