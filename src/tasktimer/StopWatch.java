package tasktimer;

public class StopWatch {
	private long startTime;
	private long stopTime;
	private boolean running;
	public void start(){
		startTime = System.nanoTime();
	
	}
	public void stop(){
		stopTime = System.nanoTime();
		
	}
	public double getElapsed(){
		return stopTime - startTime ;
	}
}
