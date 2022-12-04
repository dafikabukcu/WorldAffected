package Countries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {

    public boolean hasBugs;
    public boolean hasStreetAnimals;
    public boolean hasDirtyStreets;
    public boolean hasNarrowPlaces;
    public boolean isCold;

    public static HashMap<String, Long>countryMap = new HashMap<>();

    public World(boolean hasBugs, boolean hasStreetAnimals, boolean hasDirtyStreets, boolean hasNarrowPlaces, boolean isCold) {
        this.hasBugs = hasBugs;
        this.hasStreetAnimals = hasStreetAnimals;
        this.hasDirtyStreets = hasDirtyStreets;
        this.hasNarrowPlaces = hasNarrowPlaces;
        this.isCold = isCold;
    }
    public static long chinaPopulation = 1452687695L;
    public static long germanyPopulation = 84427027;
    public static long icelandPopulation = 372295;
    public static long indiaPopulation = 1413116698L;
    public static long italyPopulation = 60250863;
    public static long polandPopulation = 37748161;
    public static long russiaPopulation = 146083645L;
    public static long thailandPopulation = 70219693;
    public static long turkeyPopulation = 86559096;
    public static long usaPopulation = 335692946;

    public static void initializeCountryMap(){
        World.countryMap.put("China",chinaPopulation);
        World.countryMap.put("Germany",germanyPopulation);
        World.countryMap.put("Iceland",icelandPopulation);
        World.countryMap.put("India",indiaPopulation);
        World.countryMap.put("Italy",italyPopulation);
        World.countryMap.put("Poland",polandPopulation);
        World.countryMap.put("Russia",russiaPopulation);
        World.countryMap.put("Thailand",thailandPopulation);
        World.countryMap.put("Turkey",turkeyPopulation);
        World.countryMap.put("USA",usaPopulation);
    }


    public boolean doesHaveBugs(){
        return hasBugs;
    }
    public boolean doesHaveStreetAnimals(){
        return hasDirtyStreets;
    }
    public boolean doesHaveDirtyStreets(){
        return hasDirtyStreets;
    }
    public boolean doesHaveNarrowPlaces(){
        return hasNarrowPlaces;
    }
    public boolean isColdPlace(){
        return isCold;
    }
}


