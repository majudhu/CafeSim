package cafesim;

import java.util.concurrent.atomic.AtomicInteger;

public class Cupboard {
	static final long TIME_TO_OBTAIN_GLASS = 100;
	static final long TIME_TO_OBTAIN_CUP = 100;
	static final long TIME_TO_OBTAIN_MILK = 100;
	static final long TIME_TO_OBTAIN_COFFEE = 100;

	private AtomicInteger glasses = new AtomicInteger( 1 );
	private AtomicInteger    cups = new AtomicInteger( 1 );
	private AtomicInteger   milks = new AtomicInteger( 1 );
	private AtomicInteger coffees = new AtomicInteger( 1 );
	
	
	private void returnItem(AtomicInteger item) {
		item.incrementAndGet();
		item.notify();
	}
	
	private void obtainItem(AtomicInteger item) {
		while(item.get() <= 0) {
			try {
				item.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		item.decrementAndGet();
	}
	
	public void obtainGlass() throws InterruptedException {
		obtainItem(glasses);
		Thread.sleep(TIME_TO_OBTAIN_GLASS);
	}
	public void returnGlass() {
		returnItem(glasses);
	}

	public void obtainCup() throws InterruptedException {
		obtainItem(cups);
		Thread.sleep(TIME_TO_OBTAIN_CUP);
	}
	public void returnCup() {
		returnItem(cups);
	}
	
	public void obtainMilk() throws InterruptedException {
		obtainItem(milks);
		Thread.sleep(TIME_TO_OBTAIN_MILK);
	}
	public void returnMilks() {
		returnItem(milks);
	}
	
	public void obtainCoffee() throws InterruptedException {
		obtainItem(coffees);
		Thread.sleep(TIME_TO_OBTAIN_COFFEE);
	}
	public void returnCoffees() {
		returnItem(coffees);
	}
	
	

}
