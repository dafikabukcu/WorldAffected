package Countries;

import java.util.ArrayList;
import java.util.List;

public class Virus{

    public static int spreadSpeed = 8000;//50000

    public static List<World> infectedCountries = new ArrayList<>();
    public static List<World> researchCenters = new ArrayList<>();
    public static int researchCenterChance = 4;

    public static String[] transports = {"Bus", "Ship", "Plane"};

    public static int busCounter = 0;
    public static int shipCounter = 0;
    public static int planeCounter = 0;


    public Virus(){
        for (World w : infectedCountries){
            if (w.hasBugs){
                String countryName = w.getClass().getSimpleName();
                switch (countryName){

                }
            }
        }
    }

}
