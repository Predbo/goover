package goover;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class Palindrome {

	public static boolean isPalindrome(String input) {
		String a = input.toLowerCase();
		String b = new StringBuilder(a).reverse().toString();
		return a.equals(b);
	}
	
	public static void makeIt() throws Exception{
//		URL url = new URL("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=barack%20obama&userip=192.168.0.101");
		URL url = new URL("https://www.google.de/images?q=angel+falls+ayla+cover&gbv=2&tbs=isz:ex,iszw:500,iszh:500");
		
//		url = "https://www.google.de/images?q=backstreet+boys+everybody&gbv=2"
//		https://www.google.de/search?hl=de&site=imghp&tbm=isch&source=hp&biw=1173&bih=765&q=backstreet+boys+everybody&oq=backstree&gs_l=img.3.3.0l10.31725.99175.0.101868.10.9.0.1.1.0.89.479.7.7.0....0...1ac.1.64.img..2.8.484.Pm5cHU-JGao
//		
//		
		URLConnection connection = url.openConnection();
		connection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
//		connection.addRequestProperty("Referer", "http://localhost");
		
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while((line = reader.readLine()) != null) {
		builder.append(line);
		}
		
//		System.out.println(builder.toString());
//		Object obj=JSONValue.parse(builder.toString());
//		JSONArray array=(JSONArray)obj;
//		array.
		
		GoogleImagesResultParser parser = new GoogleImagesResultParser();
		parser.getImageUrlsFromHttpResponse(builder.toString());
		
	}
	
	public static void makeJson() {
		  System.out.println("=======decode=======");
          
		  String s="[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
		  Object obj=JSONValue.parse(s);
		  JSONArray array=(JSONArray)obj;
		  System.out.println("======the 2nd element of array======");
		  System.out.println(array.get(1));
		  System.out.println();
		                
		  JSONObject obj2=(JSONObject)array.get(1);
		  System.out.println("======field \"1\"==========");
		  System.out.println(obj2.get("1"));  
	}

}