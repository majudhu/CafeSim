package cafesim;

public class Clock {
	Cafe cafe;
	private long startTime;
	
	public Clock(Cafe cafe) {
		this.cafe = cafe;
		startTime = System.currentTimeMillis();
	}

	public long getTime() {
		return System.currentTimeMillis() - startTime;
	}


	public void log(String name, String info) {
		System.out.println("Thread-" + name + ": " + getTime() + ": " + ": " + info);
	}

}
