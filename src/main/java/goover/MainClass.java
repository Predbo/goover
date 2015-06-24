package goover;

public class MainClass {

	public static void main(String[] args) throws Exception {

		String dir = "/home/stefan/Musik/";
		BatchImageDownloader imageDownloader = new BatchImageDownloader();
		imageDownloader.setBaseDirectory(dir);
		imageDownloader.downloadImages();
		

	}

}
