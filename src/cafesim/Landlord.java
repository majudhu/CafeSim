package cafesim;

public class Landlord extends Server {

	public Landlord(Cafe cafe) {
		super(cafe, "Landlord");
	}
	
	public void lastOrders() {
		for (Customer customer: cafe.customers) {
			customer.lastOrders();
		}
	}


}
