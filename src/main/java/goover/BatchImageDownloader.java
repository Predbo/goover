package goover;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BatchImageDownloader {

	private final static int NUMBER_OF_IMAGES_TO_DOWNLOAD = 20;

	private String baseDir;
	private Mp3TagReader mp3TagReader = new Mp3TagReader();
	
	private ExecutorService downloadService;

	public void setBaseDirectory(String dir) {
		if (!dir.endsWith("/")) {
			dir += "/";
		}
		baseDir = dir;
	}

	public void downloadImages() throws Exception {
		downloadService = Executors.newFixedThreadPool(10);
		
		File baseDirObject = new File(baseDir);
		if (!baseDirObject.exists()) {
			throw new RuntimeException("the given directory (" + baseDir + ") does not exist!");
		}
		
		if (!baseDirObject.isDirectory()) {
			throw new RuntimeException("the given paht (" + baseDir + ") is not an directory!");
		}
		
		
		System.out.println("Free space: " + baseDirObject.getFreeSpace());
		File[] filesInBaseDir = baseDirObject.listFiles();
		
		GoogleImageSearcher googleImageSearcher = new GoogleImageSearcher();
		
		for (File file : filesInBaseDir) {
			if (file.getName().endsWith(".mp3")) {
				mp3TagReader.setMp3File(file);
				String searchString = mp3TagReader.getArtist() + " " + mp3TagReader.getTitle() + " artwork";
				searchString = replaceUnwantedCharacters(searchString);
				System.out.println("Start processing file: " + file.getName());
				List<Image> imagesUnfiltered = googleImageSearcher.searchFor(searchString );
				List<Image> imagesFiltered = new ArrayList<Image>();
				for (Image image : imagesUnfiltered) {
					if (image.getUrl().toLowerCase().endsWith("png") || image.getUrl().toLowerCase().endsWith("jpg") || image.getUrl().toLowerCase().endsWith(".jpeg")) {
						if (image.getWidth() > 290) {
							if (Math.abs(image.getWidth()-image.getHeight()) < 50) {
								imagesFiltered.add(image);
//								System.out.println("add Url: " + image);
								if (imagesFiltered.size() >= NUMBER_OF_IMAGES_TO_DOWNLOAD) {
									break;
								}
							}
						}
					}
				}
				int i = 1;
				System.out.println("Try to download " + imagesFiltered.size() + " images.");
				for (Image image : imagesFiltered) {
					String imageFileName = file.getName().substring(0, file.getName().length()-4);
					if (image.getUrl().toLowerCase().endsWith("png")) {
						imageFileName += "_img_" + i + ".png";
					} else {
						imageFileName += "_img_" + i + ".jpg";
					}
					downloadService.execute(new DownloadSimpleImageTask(image.getUrl(), new File(baseDir + imageFileName)));
					i++;
				}
			}
		}
		
		
		downloadService.shutdown();
		downloadService.awaitTermination(30, TimeUnit.SECONDS);
	}

	public String replaceUnwantedCharacters(String searchString) {
		searchString = searchString.replaceAll("[^a-zA-Z0-9\\s]", "");
		searchString = searchString.replaceAll("Edit|Radio|ft|Ft|FT|FEAT|Feat|feat|Edition|Version|short|Short", "");
		searchString = searchString.replaceAll("\\s+", " ").trim();
		return searchString;
	}

}
