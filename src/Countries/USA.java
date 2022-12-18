package Countries;

import java.text.DecimalFormat;

public class USA extends World{

    boolean hasBugs = false;
    boolean hasStreetAnimals = false;
    boolean hasDirtyStreets = false;
    boolean hasNarrowPlaces = false;
    boolean isCold = false;

    public static int population = 335692946;

    public static int virusSpreadSpeed = Virus.spreadSpeed;

    public static boolean hasLost=false;
    public static boolean hasWon=false;


    public USA() {
        super(true, false, false, false, false);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,###");
        long d = World.usaPopulation;
        String formattedNum = format.format(d);
        return "USA - Population: "+formattedNum;
    }

}
