import Countries.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PopulationRunnable implements Runnable{

    Object selectedCountry = new China();

    static int counter = 0;

    int closerCountryBonus = 5;
    static int nextWaveDay = 10;

    static boolean nextContinent = false;

    @Override
    public void run() {

        while(true){

            try{
                Thread.sleep(1000);
                Class<?> startingContinent = selectedCountry.getClass().getSuperclass();

                int ran = ThreadLocalRandom.current().nextInt(1, 10);

                World country = GameWindow.countries[ran];
                System.out.println("*****NextContinent?: "+nextContinent);
                System.out.println("Counter "+ counter);
                System.out.println("*********InfectedCountries?: "+Virus.infectedCountries.size());
                System.out.println("*************ChosenCountry?: "+country);

                if (TimerRunnable.day>nextWaveDay){
                    //System.out.println("Next Wave In: "+(nextWaveDay-TimerRunnable.day));
                    if (country.getClass().getSuperclass() == startingContinent && !Virus.infectedCountries.contains(country) && nextContinent==false){
                        infect(country);
                    }else if (!Virus.infectedCountries.contains(country) && nextContinent==true){
                        infect(country);
                    }
                    int waveRan = ThreadLocalRandom.current().nextInt(5, 8);
                    nextWaveDay = TimerRunnable.day+waveRan;

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
                        countryField.setInt(w, cntryInt*2);
                        w.isCold=false;
                        continue;
                    }

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
                        World.chinaPopulation-=China.virusSpreadSpeed;
                        System.out.println("China Virus Speed: "+China.virusSpreadSpeed);
                        break;
                    case "India":
                        World.indiaPopulation-=India.virusSpreadSpeed;
                        System.out.println("India Virus Speed: "+India.virusSpreadSpeed);
                        break;
                    case "Russia":
                        World.russiaPopulation-=Russia.virusSpreadSpeed;
                        System.out.println("Russia Virus Speed: "+Russia.virusSpreadSpeed);
                        break;
                    case "Thailand":
                        World.thailandPopulation-=Thailand.virusSpreadSpeed;
                        System.out.println("Thailand Virus Speed: "+Thailand.virusSpreadSpeed);
                        break;
                    case "Turkey":
                        World.turkeyPopulation-=Turkey.virusSpreadSpeed;
                        System.out.println("Turkey Virus Speed: "+Turkey.virusSpreadSpeed);
                        break;
                    case "Germany":
                        World.germanyPopulation-=Germany.virusSpreadSpeed;
                        System.out.println("Germany Virus Speed: "+Germany.virusSpreadSpeed);
                        break;
                    case "Iceland":
                        World.icelandPopulation-=Iceland.virusSpreadSpeed;
                        System.out.println("Iceland Virus Speed: "+Iceland.virusSpreadSpeed);
                        break;
                    case "Italy":
                        World.italyPopulation-=Italy.virusSpreadSpeed;
                        System.out.println("Italy Virus Speed: "+Italy.virusSpreadSpeed);
                        break;
                    case "Poland":
                        World.polandPopulation-=Poland.virusSpreadSpeed;
                        System.out.println("Poland Virus Speed: "+Poland.virusSpreadSpeed);
                        break;
                    case "USA":
                        World.usaPopulation-=USA.virusSpreadSpeed;
                        System.out.println("USA Virus Speed: "+USA.virusSpreadSpeed);
                        break;
                }

            }
        }


    }

    private void infect(World country) {

        Virus.infectedCountries.add(country);
        System.out.println("Infected " + country);

        String currentCountry = country.getClass().getSimpleName();
        System.out.println("!!!!!!!!!!!!!"+currentCountry);

        if (currentCountry.equals("China") || currentCountry.equals("Thailand")){
            counter++;
        }

        if ((counter>0) && Virus.infectedCountries.size()==Asia.totalCountries){
            nextContinent=true;
        }
        if ((currentCountry.equals("Germany") || currentCountry.equals("Poland")) && Virus.infectedCountries.size()==Europe.totalCountries){
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
}
