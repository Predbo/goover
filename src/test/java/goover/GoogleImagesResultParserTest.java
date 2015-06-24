package goover;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

public class GoogleImagesResultParserTest {

	GoogleImagesResultParser googleImagesResultParser = new GoogleImagesResultParser();
	
	@Test
	public void test() throws IOException {
		List<Image> imageUrlsFromHttpResponse = googleImagesResultParser.getImageUrlsFromHttpResponse(new FileInputStream("src/main/java/goover/result.html"));
		assertThat(imageUrlsFromHttpResponse.size(), Matchers.equalTo(100));
		for (Image image : imageUrlsFromHttpResponse) {
			System.out.println(image);
		}
	}
	
	@Test
	public void test2() throws IOException {
		InputStream stream = IOUtils.toInputStream("a href=\"http://www.google.de/imgres?imgurl=http://asd.de&imgrefurl=fgh.de&amp;h=500&amp;w=500&amp;\" sdaf gdfh sdf sdölk h0564 34 6+.;-df ga href=\"http://www.google.de/imgres?imgurl=http://wer.de&amp;h=500&amp;w=500&amp;&dg ", "UTF-8");
		List<Image> imageUrlsFromHttpResponse = googleImagesResultParser.getImageUrlsFromHttpResponse(stream);
		assertThat(imageUrlsFromHttpResponse.size(), Matchers.equalTo(2));
		for (Image image : imageUrlsFromHttpResponse) {
			System.out.println(image);
		}
	}
	
	@Test
	public void test3() throws IOException {
		InputStream stream = IOUtils.toInputStream("<a href=\"http://www.google.de/imgres?imgurl=http://3.bp.blogspot.com/_-wvCpXz4oAs/TLQ8hUXfw-I/AAAAAAAABqc/tYru7THOPbA/s1600/D90%252BCOVER%252B1%252B-%252BKD.jpg&amp;imgrefurl=http://mixfreaks.blogspot.com/2010_10_01_archive.html&amp;h=500&amp;w=500&amp;tbnid=QOpJNl8lsgfl_M:&amp;zoom=1&amp;docid=pfZ-0gGUGIDDMM&amp;ei=C4-JVf6zFczlUqDkg7gI&amp;tbm=isch\"");
		List<Image> imageUrlsFromHttpResponse = googleImagesResultParser.getImageUrlsFromHttpResponse(stream);
		assertThat(imageUrlsFromHttpResponse.size(), Matchers.equalTo(1));
		for (Image image : imageUrlsFromHttpResponse) {
			System.out.println(image);
		}
	}

	
}
