import java.util.Random;
import java.util.HashMap;

public class AdHocCar extends Car {
    
    public String carTypeString;
    private static int randInt(int min, int max) {
        Random randCarType = new Random();
        int randNr = randCarType.nextInt((3 - 1) + 1) + min;
    return randNr;
    }
    
    public int carTypeInt = randInt(1,3);
    
    public HashMap<String, Integer> hash = new HashMap<String, Integer>();
   
    public AdHocCar() {
    	
    }
    
    public void displayKeysValues() {
        for (String key : hash.keySet()) {
            System.out.println("HashKey = " + key);
        }
        
        for (Integer value : hash.values()) {
            System.out.println("HashValue = " + value);
        }

    
    }
}