import java.util.Random;
public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private int parkingType;
    
    /**
     * Random number generator for the payment type of Car.
     * 1 = Ad-Hoc parking
     * 2 = Short-term parking (online)
     * 3 = Long-term parking (online)
     */
    
    /**
     * Constructor for objects of class Car
     */
    public Car() {
    	
    	
    	
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public void tick() {
        minutesLeft--;
    }
    
    public void payment() {
    	
    }
}