package cafesim;

public class Customer implements Runnable {
	Integer id;
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
		for (int i = 1; i <= 3; i++) {
			log("is ordering a " + drinkType + " (" + i + "/" + 3 + ")");
			if (!cafe.order(this)) {
				log("is leaving the cafe because the order was refused");
				return;
			}
			log("is waiting for the drink");
//			synchronized(this) {
//				try {
//					wait();
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//			}
			log("i" + "s drinking " + drinkType);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log("has finished drinking " + drinkType);
		}
			
		log("is leaving the cafe");
	}

	public void lastOrders() {
		// TODO Auto-generated method stub
		
	}

}
