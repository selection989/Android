package com.thenewboston.terry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpGetExample {
	String line = "";
	HttpClient client;
	BufferedReader in;
	String responseMessage;

	public String executeRequest() throws Exception {
		try {
			URI uri = new URI("http://www.thenewboston.org");
			HttpGet myRequest = new HttpGet(uri);
			client = new DefaultHttpClient();
			HttpResponse response = client.execute(myRequest);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer b = new StringBuffer("");
			String nl = System.getProperty("line.seperator");
			while ((line = in.readLine()) != null) {
				b.append(line + nl);
			}
			responseMessage = b.toString();

		} finally {
			in.close();
			return responseMessage;
		}

	}

}
