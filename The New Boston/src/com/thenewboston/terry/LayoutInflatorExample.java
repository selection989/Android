package com.thenewboston.terry;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LayoutInflatorExample extends ListActivity {
	TextView selection;
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
			"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
			"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
			"augue", "purus" };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activitystarter);
		setListAdapter(new IconicAdapter());
		selection = (TextView) findViewById(R.id.textView2);
	}

	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) {
		selection.setText(items[position]);
	}

	class IconicAdapter extends ArrayAdapter<String> {
		IconicAdapter() {
			super(LayoutInflatorExample.this, R.layout.tabs, items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = super.getView(position, convertView, parent);

			TextView label = (TextView) row.findViewById(R.id.about);

			label.setText(items[position]);

			ImageView icon = (ImageView) row.findViewById(R.id.ivPicture);

			if (items[position].length() > 4) {
				icon.setImageResource(R.drawable.ball);
			} else {
				icon.setImageResource(R.drawable.lightningbolt);
			}

			return (row);
		}
	}
}