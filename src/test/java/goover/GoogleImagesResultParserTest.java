package goover;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class GoogleImagesResultParserTest {

	GoogleImagesResultParser googleImagesResultParser = new GoogleImagesResultParser();
	
	@Test
	public void test() throws IOException {
		FileInputStream stream = new FileInputStream("src/main/java/goover/result.html");
		String response = IOUtils.toString(stream, "UTF-8");
		googleImagesResultParser.getImageUrlsFromHttpResponse(response);
	}

}
