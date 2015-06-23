package goover;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleImagesResultParser {

	
	public List<String> getImageUrlsFromHttpResponse(String response) {
		List<String> imageUrls = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile("href=\"http://www\\.google\\.de/imgres\\?(.*)\"", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(response);

		int matches = 0;
	    while (matcher.find()) {
	    	matches++;
	        System.out.println(matcher.group(1));
//	    	imageUrls.add(matcher.group(1));
	      }
	    System.out.println("Found " + matches + " entries!");
		return imageUrls;
	}
	
	
	
}
