package cafesim;

import java.util.ArrayList;

public class Cafe {
	
	ArrayList<Table> tables;
	JuiceFountain juiceFountain;
	Cupboard cupboard; // (glasses, cups, milk and coffee)
	Clock clock;
	Landlord landlord;
	Barmaid barmaid; //	TODO create parent class server
	Assistant assistant;
	ArrayList<Customer> customers;
	
	boolean isOpen = true;;
	
	public Cafe() {
		tables = new ArrayList<Table>();
		juiceFountain = new JuiceFountain();
		clock = new Clock(this);
		cupboard = new Cupboard();
		landlord = new Landlord(this);
		barmaid = new Barmaid(this);
		assistant = new Assistant(this);
		customers = new ArrayList<Customer>();
		
	}
	
	public boolean order(Customer customer) {
		if (isOpen) {
			synchronized (customers) {
				customers.add(customer);
				clock.log("cafe", "l:" + customers.size());
				customers.notifyAll();
				return true;
			}
		}
		return false;
	}
}
