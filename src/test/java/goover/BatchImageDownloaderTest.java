package goover;

import org.junit.Test;

public class BatchImageDownloaderTest {

	BatchImageDownloader batchImageDownloader = new BatchImageDownloader();
	
	@Test
	public void test() throws Exception {
		batchImageDownloader.setBaseDirectory("/home/stefan/Musik/");
		batchImageDownloader.downloadImages();
	}
	
	@Test
	public void test2() throws Exception {
		System.out.println(batchImageDownloader.replaceUnwantedCharacters("Dr. Alban - Sing Hal (Radio Edit)"));
	}
	
}
