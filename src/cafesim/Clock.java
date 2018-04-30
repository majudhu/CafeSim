package cafesim;

public class Clock {
	static final long openTime = 1000;
	Cafe cafe;
	private long startTime;
	
	public Clock(Cafe cafe) {
		this.cafe = cafe;
		startTime = System.nanoTime()/1000;
	}

	public long getTimeElapsed() {
		return System.nanoTime()/1000 - startTime;
	}
	
	public long getTimeLeft() {
		return openTime - getTimeElapsed();
	}
	
	public String getTimeString() {
		long t = getTimeElapsed();
		return String.format("%02d.%02d.%02d", ((t/60/60)%60), ((t/60)%60), (t%60));
	}


	public void log(String name, String info) {
		System.out.println("Thread-" + name + ": " + getTimeString() + ": " + info);
	}

}
