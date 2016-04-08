import java.util.Random;

public class Simulator {	
	
	
    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 10; // number of cars that can pay per minute
    int exitSpeed = 9; // number of cars that can leave per minute

    private static int randInt(int min, int max) {
    	
            Random randCarType = new Random();
            int randNr = randCarType.nextInt((3 - 1) + 1) + min;
            		return randNr;}  
    
    public int carTypeInt = randInt(1,3);
    
    public Simulator() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
        simulatorView.getContentPane().setLayout(null);
     
    }

    public void run(int numberOfTicks) {
        for (int i = 0; i < numberOfTicks; i++) {
        	tick();
            }

    	};
    

    private void tick() {
        // Advance the time by one minute.
    	minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);

        // Add a random car (with type) to the back of the queue. (Work-in-progress)
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
        	
        	if (carTypeInt == 1) {
        		Car car = new AdHocCar();
        		entranceCarQueue.addCar(car);
        		System.out.println("Ad-Hoc Car");
        		
        	} else {
        		if (carTypeInt == 2) {
        			Car car = new LongTerm();
        			entranceCarQueue.addCar(car);
        			System.out.println("Long-term");
        			
        		} else {
        			if (carTypeInt == 3) {
        				Car car = new ShortTerm();
        				entranceCarQueue.addCar(car);
        				System.out.println("Short-term");
        				
        			}
        		}
        		}
        	}

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = simulatorView.getFirstFreeLocation();
            if (freeLocation != null) {
                simulatorView.setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }

        // Perform car park tick.

        simulatorView.tick();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = simulatorView.getFirstLeavingCar();
            if (car == null) {
                break;
            }
            car.setIsPaying(true);
            paymentCarQueue.addCar(car);
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // TODO Handle payment.
            simulatorView.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Bye!
        }

        // Update the car park view.
        simulatorView.updateView();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public void start() {
		// TODO Auto-generated method stub
		
	}
}
