import Countries.China;
import Countries.Thailand;
import Countries.Virus;
import Countries.World;

import javax.swing.*;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        // Starting the game window
        SwingUtilities.invokeLater(()-> new StartWindow());

        // Test Place
        China china = new China();
        System.out.println(china.doesHaveDirtyStreets());
        while(true){
            World.chinaPopulation--;
//            for (World w : Virus.infectedCountries){
//                String countryName = w.getClass().getSimpleName();
//                Field countryField = w.getClass().getDeclaredField("virusSpreadSpeed");
//                countryField.setAccessible(true);
//
//                double cntryInt = countryField.getDouble(w.getClass());
//                countryField.setDouble(w, cntryInt*10);
//                System.out.println("Successfuly changed "+countryField+" to "+cntryInt*10);
//                System.out.println("ThaiSpeed: "+Thailand.virusSpreadSpeed);
//            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
