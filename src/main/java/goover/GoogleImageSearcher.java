package goover;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class GoogleImageSearcher {

	public List<Image> searchFor(String searchString) throws Exception{
		// TODO Größe definieren
		// exakte Größe mit &tbs=isz:ex,iszw:500,iszh:500
		// größer als (Nur DisplayAuflösungsmaße möglich) 
		searchString = URLEncoder.encode(searchString, "UTF-8");
		URL url = new URL("https://www.google.de/images?q=" + searchString + "&gbv=2");
		System.out.println("Search google images for: " + url.toString());
		URLConnection connection = url.openConnection();
		connection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");

				
		GoogleImagesResultParser parser = new GoogleImagesResultParser();
		return parser.getImageUrlsFromHttpResponse(connection.getInputStream());
		
	}
	
}