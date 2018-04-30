package cafesim;

public abstract class Server implements Runnable {	
	final static long TIME_TO_MIX_COFFEE = 100;
	
	Cafe cafe;
	protected String name;
	private Boolean timeToLeave = false;
	
	private void log(String info) {
		cafe.clock.log(name, info);
	};
	
	public Server(Cafe cafe, String name) {
		this.cafe = cafe;
		this.name = name;
	}
	
	synchronized private void serve() throws InterruptedException {
		Customer customer = null;
		log("is recieveing a new order");
		if (cafe.customers.isEmpty()) {
			wait();
		}
		synchronized(cafe.customers) {
			customer = cafe.customers.get(0);
			cafe.customers.remove(0);
		}
		log("has recieved a new order from Customer-" + customer.id);
		log("is preparing " + customer.drink.type + " for customer-" + customer.id);
		if (customer.drink.type == "Fruit Juice") {
			log("is obtaining a glass from the cupboard");
			cafe.cupboard.obtainGlass();
			synchronized(cafe.juiceFountain ) {
				cafe.juiceFountain.dispense();
			}
		} else if (customer.drink.type == "Cuppochino") {
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
		log("is serving " + customer.drink.type + " to Customer" + customer.id);	
	}
	
	public void run() {
		while (!timeToLeave) {
			try {
				serve();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void leave() {
		timeToLeave = true;
	}
}
