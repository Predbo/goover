package goover;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class DownloadSimpleImageTaskTest {

	DownloadSimpleImageTask downloadSimpleImageTask;
	private String downloadUrl = "http://cdn2.baeldung.netdna-cdn.com/wp-content/uploads/2015/01/logo-baeldung.jpg";
	private File fileToStoreDownload;
	
	@Before
	public void setUp() {
		fileToStoreDownload = new File("/home/stefan/Musik/test.jpg");
		downloadSimpleImageTask = new DownloadSimpleImageTask(downloadUrl, fileToStoreDownload );
	}
	
	@Test
	public void test() {
		downloadSimpleImageTask.run();
	}

}
