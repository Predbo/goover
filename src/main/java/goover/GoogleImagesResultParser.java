package goover;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GoogleImagesResultParser {

	private final static Pattern GOOGLE_IMAGE_URL_PATTERN = Pattern.compile(
			"href=\"(http://www\\.google\\.de/imgres\\?imgurl=[^\"]*)",
			Pattern.CASE_INSENSITIVE);

	public List<Image> getImageUrlsFromHttpResponse(InputStream stream)
			throws MalformedURLException {
		List<Image> images = new ArrayList<Image>();
		Scanner scanner = new Scanner(stream, "UTF-8");
		int matches = 0;
		String nextMatch = null;
		do {
			nextMatch = scanner.findWithinHorizon(GOOGLE_IMAGE_URL_PATTERN, 0);
			if (nextMatch != null) {
				Image image = new Image();
				URL imageUrl = new URL(scanner.match().group(1));
				String[] queryPairs = imageUrl.getQuery().split("&amp;");
				for (String queryPair : queryPairs) {
					String[] keyValueArray = queryPair.split("=");
					if (keyValueArray[0].equalsIgnoreCase("imgurl")) {
						String url = fixUrl(keyValueArray[1]);
						image.setUrl(url);
					} else if (keyValueArray[0].equalsIgnoreCase("h")) {
						image.setHeight(Integer.parseInt(keyValueArray[1]));
					} else if (keyValueArray[0].equalsIgnoreCase("w")) {
						image.setWidth(Integer.parseInt(keyValueArray[1]));
					}
				}

				images.add(image);
				matches++;
			}
		} while (nextMatch != null);
		scanner.close();
		System.out.println("Found " + matches + " entries!");
		return images;
	}

	private String fixUrl(String urlToFix) {
		// I don't know why, but it happens that a whitespace is encoded wrongly. Maybe bug at google side???
		return urlToFix.replace("%25252520|%252520|%2520", "%20");
	}

}
