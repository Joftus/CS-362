package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class GetMapping {

	/*
	 * This is called when the UI need to access information stored in the database
	 */
	
	private static String readAll(Reader rd) {
		StringBuilder sb = new StringBuilder();
		try {
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("\nError in GetHelper");
		}
		return sb.toString();
	}

	public static String Helper(String url) throws MalformedURLException, IOException {
		url = URLs.baseURL + url; 
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String json = readAll(rd);
			return json;
		}
		finally {
			is.close();
		}
	}
}
