package tasktimer;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 implements Runnable{
	BufferedReader br = null;

	public Task2(){
		try {
			br = new BufferedReader( new InputStreamReader(Dictionary.getWordsAsStream()) );
		} catch (Exception ex) {
			out.println("Could not open dictionary: "+ex.getMessage());
			return;
		}
	}
	@Override
	public void run() {


		try {
			int count = 0;
			long totalsize = 0;
			String word = null;
			while( (word=br.readLine()) != null ) {
				totalsize += word.length();
				count++;
			}
			double averageLength = ((double)totalsize)/(count>0 ? count : 1);
			out.printf("Average length of %,d words is %.2f\n", count, averageLength);  
		} catch(IOException ioe) {
			out.println(ioe);
			return;
		} finally {
			try { br.close(); } catch (Exception ex) { /* ignore it */ }
		}


	}

	public String toString(){
		return "Starting task: read words using BufferedReader.readLine() with a loop";
	}


}
