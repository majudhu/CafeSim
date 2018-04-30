package cafesim;

public class Main {

	public static void main(String[] args) {
		Cafe cafe = new Cafe();
		CustomerGenerator cg = new CustomerGenerator(cafe);
		cg.run();
		cafe.landlord.run();

	}

}
