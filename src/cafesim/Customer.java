package cafesim;

public class Customer implements Runnable {
	Integer id;
	Drink drink;
	Cafe cafe;
	String drinkType;
	Boolean extra;
	
	public Customer(int id, Cafe cafe, String drinkType, Boolean extra) {
		this.id = id;
		this.cafe = cafe;
		this.drinkType = drinkType;
		this.extra = extra;
	}
	
	private void log(String info) {
		cafe.clock.log("Customer-" + id, info);
	}

	public void run() {
		log("is entering the cafe");
		for (int i = 0; i <= 5; i++) {
			log("is ordering a " + drinkType);
			if (!cafe.order(this)) {
				log("is leaving the cafe because the order was refused");
				return;
			}
			log("is drinking " + drinkType);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log("has finished drinking " + drinkType);
		}
			
		log("is leaving the cafe");
	}

}
