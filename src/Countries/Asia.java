package Countries;

public class Asia extends World{

    boolean hasBugs;
    boolean hasStreetAnimals;
    boolean hasDirtyStreets;
    boolean hasNarrowPlaces;
    boolean isCold;

    public static long totalPopulation = 4641054775L;

    public static int totalCountries = 5;

    public Asia(boolean hasBugs, boolean hasStreetAnimals, boolean hasDirtyStreets, boolean hasNarrowPlaces, boolean isCold) {
        super(hasBugs, hasStreetAnimals, hasDirtyStreets, hasNarrowPlaces, isCold);
        this.hasBugs = hasBugs;
        this.hasStreetAnimals = hasStreetAnimals;
        this.hasDirtyStreets = hasDirtyStreets;
        this.hasNarrowPlaces = hasNarrowPlaces;
        this.isCold = isCold;
    }

//    public boolean doesHaveBugs(){
//        return hasBugs;
//    }
//    public boolean doesHaveStreetAnimals(){
//        return hasDirtyStreets;
//    }
//    public boolean doesHaveDirtyStreets(){
//        return hasDirtyStreets;
//    }
//    public boolean doesHaveNarrowPlaces(){
//        return hasNarrowPlaces;
//    }
//    public boolean isColdPlace(){
//        return isCold;
//    }
}
