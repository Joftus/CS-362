package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostMapping {

	/*
	 * This class handles all situations where we need to manipulate
	 * or create data objects in the database
	 */
	
	public static String Helper(String targetURL) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(URLs.baseURL + targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String jsonText = readAll(rd);
			rd.close();
			return jsonText;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in PostMapping.Helper");
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
