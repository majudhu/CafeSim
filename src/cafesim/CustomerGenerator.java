package cafesim;

import java.util.Random;

public class CustomerGenerator implements Runnable {
	static final int NUMBER_OF_CUSTOMERS = 5;
	static final int RATIO_OF_CAPPUCCINO_DRINKERS = 2;
	static final int RATIO_OF_CHOCOLATE_DRINKERS = 3;
	static final int RATIO_OF_FRUIT_JUICE_DRINKERS = 4;
	static final double SUM_OF_RATIOS = RATIO_OF_CAPPUCCINO_DRINKERS + RATIO_OF_CHOCOLATE_DRINKERS + RATIO_OF_FRUIT_JUICE_DRINKERS;
	static final double PROBABILITY_OF_ONE_MORE_EXTRA_DRINKER = 0.5;
	// CP is the cumulative probability, last CP should be equal to 1.0
	static final double CP_OF_CAPPUCCINO_DRINKER = RATIO_OF_CAPPUCCINO_DRINKERS / SUM_OF_RATIOS;
	static final double CP_OF_CHOCOLATE_DRINKER = CP_OF_CAPPUCCINO_DRINKER + RATIO_OF_CHOCOLATE_DRINKERS / SUM_OF_RATIOS;
	static final double CP_OF_FRUIT_JUICE_DRINKER = CP_OF_CHOCOLATE_DRINKER + RATIO_OF_FRUIT_JUICE_DRINKERS / SUM_OF_RATIOS;

	Cafe cafe;
	public String drinkType;
	Random random = new Random();
	
	
	public CustomerGenerator (Cafe cafe) {
		this.cafe = cafe;
	}
	
	public void run() {
		for (int id = 1; id <= NUMBER_OF_CUSTOMERS; id++) {
			double random_number = random.nextDouble();
			if (random_number < CP_OF_CAPPUCCINO_DRINKER) {
				drinkType = "cuppochino";
			} else if (random_number < CP_OF_CHOCOLATE_DRINKER) {
				drinkType = "chocolate";
			} else {
				drinkType = "fruit juice";
			}
			Boolean extra = (random.nextDouble() < PROBABILITY_OF_ONE_MORE_EXTRA_DRINKER);
			
			Customer customer = new Customer(id, cafe, drinkType, extra);
			customer.run();
			
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}
