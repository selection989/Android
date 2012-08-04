package com.thenewboston.terry;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class HttpJSONExample extends Activity {
	HttpClient client;
	TextView tvResults;
	JSONObject object;

	final static String URL = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexample);
		tvResults = (TextView) findViewById(R.id.tvResult);
		client = new DefaultHttpClient();
		new JSONReader().execute("text");

	}

	public JSONObject getlastTweet(String username)
			throws ClientProtocolException, IOException, JSONException {
		JSONObject responseJSONObject = null;
		HttpGet get = new HttpGet(URL + username);
		HttpResponse response = client.execute(get);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			String data = EntityUtils.toString(entity);
			JSONArray json = new JSONArray(data);
			responseJSONObject = json.getJSONObject(0);
		}
		System.out.println(responseJSONObject);
		return responseJSONObject;

	}

	public class JSONReader extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String response = null;
			try {
				JSONObject object = getlastTweet("mybringback");

				response = object.getString(params[0]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {

			tvResults.setText(result);
		}
	}

}
