import Countries.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PopulationRunnable implements Runnable{

    Object selectedCountry = new China();



    int closerCountryBonus = 5;

    @Override
    public void run() {

        while(true){

            try{
                Thread.sleep(1000);
                System.out.println("Booo");
                Class<?> startingContinent = selectedCountry.getClass().getSuperclass();

                int ran = ThreadLocalRandom.current().nextInt(1, 10);
                System.out.println("RandomNum: "+ ran);

                World country = GameWindow.countries[ran];
                if (TimerRunnable.day<30){

                    if (country.getClass().getSuperclass() == startingContinent && !Virus.infectedCountries.contains(country)){
                        infect(country);
                    }

                }

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
                    }
                    if (w.hasDirtyStreets){
                        countryField.setInt(w, cntryInt*2);
                        w.hasDirtyStreets=false;
                    }
                    if (w.hasStreetAnimals){
                        countryField.setInt(w, cntryInt*2);
                        w.hasStreetAnimals=false;
                    }
                    if (w.hasNarrowPlaces){
                        countryField.setInt(w, cntryInt*2);
                        w.hasNarrowPlaces=false;
                    }
                    if (w.isCold){
                        countryField.setInt(w, cntryInt*2);
                        w.isCold=false;
                    }
//                    if (World.countryMap.containsKey(countryName)){
//                        Long population = World.countryMap.get(countryName);
//                        Long newPopulation = population-cntryInt;
//                        Set<Map.Entry<String, Long>> countries =  World.countryMap.entrySet();
//                        for (Map.Entry<String, Long> k : countries){
//                            if (k.getKey().equals(countryName)){
//                                World.countryMap.replace(countryName, k.getValue(), newPopulation);
//                            }
//                        }
//                    }
                }

                if (selectedCountry.getClass().getSuperclass() == Europe.class){
                    //Virus.spreadSpeed *= closerCountryBonus;
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
                        System.out.println(China.virusSpreadSpeed);
                        break;
                    case "India":
                        World.indiaPopulation-=India.virusSpreadSpeed;
                        System.out.println(India.virusSpreadSpeed);
                        break;
                    case "Russia":
                        World.russiaPopulation-=Russia.virusSpreadSpeed;
                        System.out.println(Russia.virusSpreadSpeed);
                        break;
                    case "Thailand":
                        World.thailandPopulation-=Thailand.virusSpreadSpeed;
                        System.out.println(Thailand.virusSpreadSpeed);
                        break;
                    case "Turkey":
                        World.turkeyPopulation-=Turkey.virusSpreadSpeed;
                        System.out.println(Turkey.virusSpreadSpeed);
                        break;
                    case "Germany":
                        World.germanyPopulation-=Germany.virusSpreadSpeed;
                        System.out.println(Germany.virusSpreadSpeed);
                        break;
                    case "Iceland":
                        World.icelandPopulation-=Iceland.virusSpreadSpeed;
                        System.out.println(Iceland.virusSpreadSpeed);
                        break;
                    case "Italy":
                        World.italyPopulation-=Italy.virusSpreadSpeed;
                        System.out.println(Italy.virusSpreadSpeed);
                        break;
                    case "Poland":
                        World.polandPopulation-=Poland.virusSpreadSpeed;
                        System.out.println(Poland.virusSpreadSpeed);
                        break;
                    case "USA":
                        World.usaPopulation-=USA.virusSpreadSpeed;
                        System.out.println(USA.virusSpreadSpeed);
                        break;
                }

            }
        }


    }

    private void infect(World country) {

        Virus.infectedCountries.add(country);
        System.out.println("Infected " + country);

    }
}
