package goover;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;

public class DownloadSimpleImageTask implements Runnable {

	private final String downloadUrl;
	private final File fileToStoreDownload;
	
	public DownloadSimpleImageTask(String downloadUrl, File fileToStoreDownload) {
		this.downloadUrl = downloadUrl;
		this.fileToStoreDownload = fileToStoreDownload;
	}

	public void run() {
//		System.out.println("Start downloading from " + downloadUrl + " to " + fileToStoreDownload.getName());
		URL url;
		try {
			url = new URL(downloadUrl);
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
			FileUtils.copyInputStreamToFile(connection.getInputStream(), fileToStoreDownload);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
