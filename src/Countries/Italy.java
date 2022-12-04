package Countries;

import java.text.DecimalFormat;

public class Italy extends Europe{

    public static int population = 60250863;

    public static int virusSpreadSpeed = Virus.spreadSpeed;


    public Italy() {
        super(false,false,false,true,false);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.italyPopulation;
        String formattedNum = format.format(d);
        return "Italy - Population: "+formattedNum;
    }
}
