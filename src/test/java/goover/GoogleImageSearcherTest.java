package goover;

import org.junit.Test;

public class GoogleImageSearcherTest {

	GoogleImageSearcher googleImageSearcher = new GoogleImageSearcher();

	@Test
	public void makeItTest() throws Exception {
		String searchString = "Mr. Angel falls ayla cover";
		googleImageSearcher.searchFor(searchString);
	}
}
