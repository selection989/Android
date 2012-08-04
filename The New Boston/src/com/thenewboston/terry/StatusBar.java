package com.thenewboston.terry;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusBar extends Activity implements OnClickListener {
	Button bStatusBar;
	NotificationManager nm;
	static final int UNIQUEID = 12321;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.statusbar);
		bStatusBar = (Button) findViewById(R.id.bStatusBar);
		bStatusBar.setOnClickListener(this);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(UNIQUEID);
	}

	public void onClick(View arg0) {
		Intent intent = new Intent(this, StatusBar.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		String body = "This is a message from Travis, thanks for your support";
		String title = "Travis C.";
		Notification n = new Notification(R.drawable.lightningbolt, body,
				System.currentTimeMillis());
		n.setLatestEventInfo(this, title, body, pi);
		n.defaults = Notification.DEFAULT_ALL;
		nm.notify(UNIQUEID, n);
		finish();
	}
}
