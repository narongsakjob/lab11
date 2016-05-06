package tasktimer;

import java.io.InputStream;

public class Dictionary {

	public static InputStream getWordsAsStream() {

		InputStream instream = TaskTimer.class.getClassLoader().getResourceAsStream("wordlist.txt");
		return instream;

	}
}
