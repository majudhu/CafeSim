package cafesim;

public abstract class Server implements Runnable {	
	final static long TIME_TO_MIX_COFFEE = 100;
	
	Cafe cafe;
	protected String name;
	private Boolean timeToLeave = false;
	
	private void log(String info) {
		cafe.clock.log(name, info);
	}
	
	public Server(Cafe cafe, String name) {
		this.cafe = cafe;
		this.name = name;
	}
	
	 private void serve() throws InterruptedException {
		Customer customer = null;
		synchronized(cafe.customers) {
			while (customer == null) {
				if (!cafe.customers.isEmpty()) {
					log("is recieveing a new order");
					customer = cafe.customers.remove(0);
				} else {
					log("is waiting for a new order");
					cafe.customers.wait();
				}
			}
		}
		log("has recieved a new order from Customer-" + customer.id);
		log("is preparing " + customer.drinkType + " for customer-" + customer.id);
		if (customer.drinkType == "Fruit Juice") {
			log("is obtaining a glass from the cupboard");
			cafe.cupboard.obtainGlass();
			log("is filling the glass from the juicefountain");
			cafe.juiceFountain.dispense();
		} else if (customer.drinkType == "Cuppochino") {
			log("is obtaining cup from the cupboard");
			cafe.cupboard.obtainCup();
			log("is obtaining milk from the cupboard");
			cafe.cupboard.obtainMilk();
			log("is obtaining coffee from the cupboard");
			cafe.cupboard.obtainCoffee();
			log("is mixing the drink");
			Thread.sleep(TIME_TO_MIX_COFFEE);
			log("is returning the milk to the cupboard");
			cafe.cupboard.obtainMilk();
			log("is returning the coffee to the cupboard");
			cafe.cupboard.obtainCoffee();
		}
		log("is serving " + customer.drinkType + " to Customer" + customer.id);	
		
		customer.notify();
	}
	
	public void run() {
		log("is serving");
		while (!timeToLeave) {
			try {
				serve();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log("is leaving");
	}
	
	protected void leave() {
		timeToLeave = true;
	}
}
