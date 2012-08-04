package com.thenewboston.terry;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherXmlExample extends Activity implements OnClickListener {
	Button bSubmit;
	EditText etState, etCity;
	TextView tvResults;
	final String BASE_URL = "http://www.google.com/ig/api?weather=";
	final String COMMA = ",";
	String city, state;

	StringBuilder sb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		bSubmit = (Button) findViewById(R.id.bSubmit);
		etState = (EditText) findViewById(R.id.etState);
		etCity = (EditText) findViewById(R.id.etCity);
		tvResults = (TextView) findViewById(R.id.tvResults);
		bSubmit.setOnClickListener(this);
	}

	public void onClick(View v) {
		city = etCity.getText().toString();
		state = etState.getText().toString();

		new DoTheWork().execute();

	}

	public class DoTheWork extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			sb = new StringBuilder(BASE_URL);
			sb.append(city);
			sb.append(COMMA);
			sb.append(state);
			String response = null;
			try {
				URL myUrl = new URL(sb.toString());
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader parser = sp.getXMLReader();
				GetXmlData slave = new GetXmlData();
				parser.setContentHandler(slave);
				parser.parse(new InputSource(myUrl.openStream()));
				response = slave.getData();

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
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

			super.onPostExecute(result);
			tvResults.setText(result);
		}

	}

}
