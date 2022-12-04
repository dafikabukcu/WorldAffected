package Countries;

import java.util.ArrayList;
import java.util.List;

public class Virus{

    public static int spreadSpeed = 10;

    public static List<World> infectedCountries = new ArrayList<>();


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
