import Countries.*;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PopulationRunnable implements Runnable{

    static Object selectedCountry;

    List<World> eliminatedCountries = new ArrayList<>();
    static int asiaCounter = 0;
    static int europeCounter = 0;

    int closerCountryBonus = 5;
    static int nextWaveDay = 10;

    static boolean nextContinent = false;

    @Override
    public void run() {

        while(GameWindow.isRunning){

            try{
                Thread.sleep(1000);
                Class<?> startingContinent = selectedCountry.getClass().getSuperclass();
                if (GameWindow.ctrlPressed || GameWindow.shiftPressed || GameWindow.qPressed)
                    System.exit(0);

                int ran = ThreadLocalRandom.current().nextInt(0, 10);

                World country = GameWindow.countries[ran];
                System.out.println("*****NextContinent?: "+nextContinent);
                System.out.println("Asia Counter "+ asiaCounter);
                System.out.println("EU Counter "+ asiaCounter);
                System.out.println("*********InfectedCountries?: "+Virus.infectedCountries.size());
                System.out.println("*************ChosenCountry?: "+country);
                System.out.println("****Opened Research Center Countries\n"+Virus.researchCenters);
                System.out.println("****HEALED PEOPLE: "+World.healedPeople);
                System.out.println("****WON COUNTRIES: "+World.wonCountries);
                System.out.println("****LOST COUNTRIES: "+World.lostCountries);
                //System.out.println("****HEAL SPEED: "+600*TimerRunnable.day);

                if (TimerRunnable.day>nextWaveDay){
                    //System.out.println("Next Wave In: "+(nextWaveDay-TimerRunnable.day));
                    if (country.getClass().getSuperclass() == startingContinent && !Virus.infectedCountries.contains(country) && nextContinent==false){
                        infect(country);
                    }else if (!Virus.infectedCountries.contains(country) && nextContinent==true){
                        infect(country);
                    }else{
                        if (TimerRunnable.day>100 && country.getClass().getSimpleName() == "USA"){
                            infect(country);
                        }
                    }
                    int waveRan = ThreadLocalRandom.current().nextInt(5, 8);
                    nextWaveDay = TimerRunnable.day+waveRan;

                    if (TimerRunnable.day>30 || Virus.infectedCountries.size()>3){
                        int rand = ThreadLocalRandom.current().nextInt(0, 12);
                        System.out.println("RESEARCH CENTER RAND: "+ rand);
                        System.out.println("RESEARCH CENTER CHANCE: "+Virus.researchCenterChance);
                        if(rand<Virus.researchCenterChance){
                            openResearchCenter(country);
                        }
                    }
                }
                if ((World.wonCountries>World.lostCountries) && (World.wonCountries+World.lostCountries==10)){
                    // Add notification that user won
                    sendPopUpMsg("You won!");
                    SwingUtilities.invokeLater(()-> new SaveWindow());

                }else if ((World.wonCountries<World.lostCountries) && (World.wonCountries+World.lostCountries==10)){
                    // Add notification that user lost
                    sendPopUpMsg("You lost.");
                    SwingUtilities.invokeLater(()-> new SaveWindow());

                }else{

                }
                if (Virus.researchCenters.size()>0){
                    for (World w : Virus.infectedCountries){
//                        Field countryField = w.getClass().getDeclaredField("virusSpreadSpeed");
//                        countryField.setAccessible(true);
//                        int cntryInt = countryField.getInt(w.getClass());
//                        int healedPeople = 600*TimerRunnable.day;
//                        countryField.setInt(w, cntryInt-healedPeople);
//                        World.healedPeople+= healedPeople;

                        Field countryField = w.getClass().getDeclaredField("virusSpreadSpeed");
                        countryField.setAccessible(true);
                        int cntryInt = countryField.getInt(w.getClass());
                        //countryField.setInt(w, cntryInt-(cntryInt/100*20));

                        String countryName = w.getClass().getSimpleName();

                        int healSpeed = ((cntryInt/100)*3)*Virus.researchCenters.size();
                        switch (countryName){
                            case "China":
                                if (China.hasWon || China.hasLost){
                                    break;
                                }
                                if (World.chinaPopulation<0 && !China.hasLost){
                                    World.chinaPopulation=0;
                                    World.lostCountries++;
                                    China.hasLost=true;
                                }
                                if (cntryInt<=healSpeed && !China.hasWon){
                                    System.out.println("Virus cleared on China.");
                                    countryField.setInt(w, 0);
                                    China.hasWon=true;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.chinaPopulation+=healSpeed;
                                }
                                //countryField.setInt(w, cntryInt-healSpeed);
//                                World.chinaPopulation -= healSpeed;
//                                World.chinaPopulation -= (World.chinaPopulation/100)*10;
                                break;
                            case "Germany":
                                if (Germany.hasWon || Germany.hasLost){
                                    break;
                                }
                                if (World.germanyPopulation<0 && !Germany.hasLost){
                                    World.germanyPopulation=0;
                                    World.lostCountries++;
                                    Germany.hasLost=true;
                                    break;
                                }
                                if (cntryInt<=healSpeed && !Germany.hasWon){
                                    System.out.println("Virus cleared on Germany.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    Germany.hasWon=true;
                                    break;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.germanyPopulation+=healSpeed;
                                }
                                break;
                            case "Iceland":
                                if (Iceland.hasWon || Iceland.hasLost){
                                    break;
                                }
                                if (World.icelandPopulation<0 & !Iceland.hasLost){
                                    World.icelandPopulation=0;
                                    World.lostCountries++;
                                    Iceland.hasLost=true;
                                    break;
                                }
                                if (cntryInt<=healSpeed & !Iceland.hasWon){
                                    System.out.println("Virus cleared on Iceland.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    Iceland.hasWon=true;
                                    break;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.icelandPopulation+=healSpeed;
                                }
                                break;
                            case "India":
                                if (India.hasWon || India.hasLost){
                                    break;
                                }
                                if (World.indiaPopulation<0 && !India.hasLost){
                                    World.indiaPopulation=0;
                                    World.lostCountries++;
                                    India.hasLost=true;
                                    break;
                                }
                                if (cntryInt<=healSpeed && !India.hasWon){
                                    System.out.println("Virus cleared on India.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    India.hasWon=true;
                                    break;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.indiaPopulation+=healSpeed;
                                }
                                break;
                            case "Italy":
                                if (Italy.hasWon || Italy.hasLost){
                                    break;
                                }
                                if (World.italyPopulation<0 && !Italy.hasLost){
                                    World.italyPopulation=0;
                                    World.lostCountries++;
                                    Italy.hasLost=true;
                                    break;
                                }
                                if (cntryInt<=healSpeed && !Italy.hasWon){
                                    System.out.println("Virus cleared on Italy.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    Italy.hasWon=true;
                                    break;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.italyPopulation+=healSpeed;
                                }
                                break;
                            case "Poland":
                                if (Poland.hasWon || Poland.hasLost){
                                    break;
                                }
                                if (World.polandPopulation<0 && !Poland.hasLost){
                                    World.polandPopulation=0;
                                    World.lostCountries++;
                                    Poland.hasLost=true;
                                    break;
                                }
                                if (cntryInt<=healSpeed && !Poland.hasWon){
                                    System.out.println("Virus cleared on Poland.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    Poland.hasWon=true;
                                    break;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.polandPopulation+=healSpeed;
                                }
                                break;
                            case "Russia":
                                if (Russia.hasWon || Russia.hasLost){
                                    break;
                                }
                                if (World.russiaPopulation<0 && !Russia.hasLost){
                                    World.russiaPopulation=0;
                                    World.lostCountries++;
                                    Russia.hasLost=true;
                                }else if (cntryInt<=healSpeed && !Russia.hasWon){
                                    System.out.println("Virus cleared on Russia.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    Russia.hasWon=true;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.russiaPopulation+=healSpeed;
                                }
                                break;
                            case "Thailand":
                                if (Thailand.hasWon || Thailand.hasLost){
                                    break;
                                }
                                if (World.thailandPopulation<0 && !Thailand.hasLost){
                                    World.thailandPopulation=0;
                                    World.lostCountries++;
                                    Thailand.hasLost=true;
                                }else if (cntryInt<=healSpeed && !Thailand.hasWon){
                                    System.out.println("Virus cleared on Thailand.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    Thailand.hasWon=true;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.thailandPopulation+=healSpeed;
                                }
                                break;
                            case "Turkey":
                                if (Turkey.hasWon || Turkey.hasLost){
                                    break;
                                }
                                if (World.turkeyPopulation<0 && !Turkey.hasLost){
                                    World.turkeyPopulation=0;
                                    World.lostCountries++;
                                    Turkey.hasLost=true;
                                }else if (cntryInt<=healSpeed && !Turkey.hasWon){
                                    System.out.println("Virus cleared on Turkey.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    Turkey.hasWon=true;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.turkeyPopulation+=healSpeed;
                                }
                                break;
                            case "USA":
                                if (USA.hasWon || USA.hasLost){
                                    break;
                                }
                                if (World.usaPopulation<0 && !USA.hasLost){
                                    World.usaPopulation=0;
                                    World.lostCountries++;
                                    USA.hasLost=true;
                                }else if (cntryInt<=healSpeed && !USA.hasWon){
                                    System.out.println("Virus cleared on USA.");
                                    countryField.setInt(w, 0);
                                    World.wonCountries++;
                                    USA.hasWon=true;
                                }else{
                                    World.healedPeople += healSpeed;
                                    World.usaPopulation+=healSpeed;
                                }
                                break;

                        }
                    }
                }
                System.out.println("Next Wave On: "+(nextWaveDay));

                // SET VIRUS SPEED PER CLASS/COUNTRY.
                for (World w : Virus.infectedCountries){
                    String countryName = w.getClass().getSimpleName();
                    Field countryField = w.getClass().getDeclaredField("virusSpreadSpeed");
                    countryField.setAccessible(true);
                    int cntryInt = countryField.getInt(w.getClass());
                    //System.out.println("ThaiSpeed: "+Thailand.virusSpreadSpeed);
                    if ((w.hasBugs)){
                        countryField.setInt(w, cntryInt*2);
                        w.hasBugs=false;
                        continue;
                    }
                    if (w.hasDirtyStreets){
                        countryField.setInt(w, cntryInt*2);
                        w.hasDirtyStreets=false;
                        continue;
                    }
                    if (w.hasStreetAnimals){
                        countryField.setInt(w, cntryInt*2);
                        w.hasStreetAnimals=false;
                        continue;
                    }
                    if (w.hasNarrowPlaces){
                        countryField.setInt(w, cntryInt*2);
                        w.hasNarrowPlaces=false;
                        continue;
                    }
                    if (w.isCold){
                        countryField.setInt(w, cntryInt-5000);
                        w.isCold=false;
                        continue;
                    }

                }

                for (World w : Virus.researchCenters){
                    System.out.println("Research Going On in: "+w);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            for (World w : Virus.infectedCountries){
                String countryName = w.getClass().getSimpleName();
                switch (countryName){
                    case "China":
                        if (World.chinaPopulation>0 && !China.hasLost && !China.hasWon){
                            World.chinaPopulation-=China.virusSpreadSpeed;
                            //World.totalVirusCount+=China.virusSpreadSpeed;
                            System.out.println("China Virus Speed: "+China.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.chinaPopulation=0;
                            }
                        }
                        break;
                    case "India":
                        if (World.indiaPopulation>0 && !India.hasLost && !India.hasWon){
                            World.indiaPopulation-=India.virusSpreadSpeed;
                            System.out.println("India Virus Speed: "+India.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.indiaPopulation=0;
                            }
                        }
                        break;
                    case "Russia":
                        if (World.russiaPopulation>0 && !Russia.hasLost && !Russia.hasWon){
                            World.russiaPopulation-=Russia.virusSpreadSpeed;
                            System.out.println("Russia Virus Speed: "+Russia.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.russiaPopulation=0;
                            }
                        }
                        break;
                    case "Thailand":
                        if (World.thailandPopulation>0 && !Thailand.hasLost && !Thailand.hasWon){
                            World.thailandPopulation-=Thailand.virusSpreadSpeed;
                            System.out.println("Thai Virus Speed: "+Thailand.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.thailandPopulation=0;
                            }
                        }
                        break;
                    case "Turkey":
                        if (World.turkeyPopulation>0 && !Turkey.hasLost && !Turkey.hasWon){
                            World.turkeyPopulation-=Turkey.virusSpreadSpeed;
                            System.out.println("Turkey Virus Speed: "+Turkey.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.turkeyPopulation=0;
                            }
                        }
                        break;
                    case "Germany":
                        if (World.germanyPopulation>0 && !Germany.hasLost && !Germany.hasWon){
                            World.germanyPopulation-=Germany.virusSpreadSpeed;
                            System.out.println("Germany Virus Speed: "+Germany.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.germanyPopulation=0;
                            }
                        }
                        break;
                    case "Iceland":
                        if (World.icelandPopulation>0 && !Iceland.hasLost && !Iceland.hasWon){
                            World.icelandPopulation-=Iceland.virusSpreadSpeed;
                            System.out.println("Iceland Virus Speed: "+Iceland.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.icelandPopulation=0;
                            }
                        }
                        break;
                    case "Italy":
                        if (World.italyPopulation>0 && !Italy.hasLost && !Italy.hasWon){
                            World.italyPopulation-=Italy.virusSpreadSpeed;
                            System.out.println("Italy Virus Speed: "+Italy.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.italyPopulation=0;
                            }
                        }
                        break;
                    case "Poland":
                        if (World.polandPopulation>0 && !Poland.hasLost && !Poland.hasWon){
                            World.polandPopulation-=Poland.virusSpreadSpeed;
                            System.out.println("Poland Virus Speed: "+Poland.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.polandPopulation=0;
                            }
                        }
                        break;
                    case "USA":
                        if (World.usaPopulation>0 && !USA.hasLost && !USA.hasWon){
                            World.usaPopulation-=USA.virusSpreadSpeed;
                            System.out.println("USA Virus Speed: "+USA.virusSpreadSpeed);
                        }else{
                            if (!eliminatedCountries.contains(w)){
                                eliminatedCountries.add(w);
                                World.usaPopulation=0;
                            }
                        }
                        break;
                }

            }
        }


    }

    public static void infect(World country) {

        Virus.infectedCountries.add(country);
        System.out.println("Infected " + country);

        String currentCountry = country.getClass().getSimpleName();
        System.out.println("!!!!!!!!!!!!!"+currentCountry);

        if (currentCountry.equals("China") || currentCountry.equals("Thailand")){
            asiaCounter++;
        }

        if (currentCountry.equals("Germany") || currentCountry.equals("Poland")){
            europeCounter++;
        }

        if ((asiaCounter>0) && Virus.infectedCountries.size()==Asia.totalCountries){
            nextContinent=true;
        }
        if ((europeCounter>0) && Virus.infectedCountries.size()==Europe.totalCountries){
            nextContinent=true;
        }

        int transportRan = ThreadLocalRandom.current().nextInt(0, 2);
        String option = Virus.transports[transportRan];
        if (option=="Bus"){
            System.out.println("Infected because of a bus trip");
            Virus.busCounter++;
        }
        if (option=="Ship"){
            System.out.println("Infected because of a ship travel");
            Virus.shipCounter++;
        }
        if (option=="Plane"){
            System.out.println("Infected because of a plane travel");
            Virus.planeCounter++;
        }
        System.out.println("Current Stats:"+
                "\nBus Infected: "+Virus.busCounter+
                "\nShip Infected: "+Virus.shipCounter+
                "\nPlane Infected: "+Virus.planeCounter
        );

    }

    public static void openResearchCenter(World country){
        System.out.println("A research center just opened in "+country.getClass().getSimpleName());
        Virus.researchCenters.add(country);
    }

    public static void sendPopUpMsg(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
