package tasktimer;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class Task4 implements Runnable{

	@Override
	public void run() {
		  BufferedReader br = null;
	        try {
	            br = new BufferedReader( new InputStreamReader(Dictionary.getWordsAsStream()) );
	        } catch (Exception ex) {
	            out.println("Could not open dictionary: "+ex.getMessage());
	            return;
	        }
	        
	        
	        // We want the Consumer to add to the count and total length,
	        // but a Lambda can only access local variables (from surrounding scope) if
	        // they are final.  That means, we can't use an int, long, or double variable. 
	        // So, use AtomicInteger and AtomicLong, which are mutable objects.
	        final AtomicLong total = new AtomicLong();
	        final AtomicInteger counter = new AtomicInteger();
	        //TODO Use a Collector instead of Consumer
	        Consumer<String> consumer = new Consumer<String>() {
	            public void accept(String word) {
	                total.getAndAdd( word.length() );
	                counter.incrementAndGet();
	            }
	        };
	                
	        br.lines().forEach( consumer );  // Ha! No loop.
	        // close the input
	        try { br.close(); } catch(IOException ex) { /* ignore it */ }
	        int count = counter.intValue();
	        double averageLength = (count > 0) ? total.doubleValue()/count : 0.0;
	        out.printf("Average length of %,d words is %.2f\n", count, averageLength );

	}
	
	public String toString(){
		return "Starting task: read words using BufferedReader and Stream with Collector";
	}

}
