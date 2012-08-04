package com.thenewboston.terry;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Maps extends MapActivity implements LocationListener {
	MapView mvMain;
	float start;
	float stop;
	MyLocationOverlay myPin;
	MapController myController;
	int x, y, myLong, myLat;
	Geocoder myCoder;
	GeoPoint myPoint;
	Drawable d;
	List<Overlay> overlayList;
	LocationManager lm;
	String towers;
	GeoPoint myLocation;

	@Override
	protected void onResume() {
		myPin.enableCompass();
		super.onResume();
		lm.requestLocationUpdates(towers, 500, 1, this);
	}

	@Override
	protected void onPause() {
		myPin.disableCompass();
		super.onPause();
		lm.removeUpdates(this);
	}

	@Override
	protected void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.maps);
		mvMain = (MapView) findViewById(R.id.mvMain);
		mvMain.setBuiltInZoomControls(true);
		Touchy t = new Touchy();
		overlayList = mvMain.getOverlays();
		overlayList.add(t);
		myPin = new MyLocationOverlay(Maps.this, mvMain);
		overlayList.add(myPin);
		myController = mvMain.getController();
		myController.setZoom(15);
		GeoPoint myGeoPoint = new GeoPoint(50000, 100000);

		d = getResources().getDrawable(R.drawable.pin_blue);
		// placing pinpoint at location
		Criteria crit = new Criteria();
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		towers = lm.getBestProvider(crit, false);
		Location location = lm.getLastKnownLocation(towers);
		if (location != null) {
			myLong = (int) (location.getLongitude() * 1E6);
			myLat = (int) (location.getLatitude() * 1E6);
			myLocation = new GeoPoint(myLat, myLong);
			CustomPinPoint pinPoints = new CustomPinPoint(d, Maps.this);
			OverlayItem pinPoint = new OverlayItem(myLocation, "Hey guys",
					"Whatsup");
			pinPoints.addPinPoint(pinPoint);
			overlayList.add(pinPoints);
			mvMain.invalidate();
			myController.animateTo(myLocation);
		} else {
			Toast.makeText(getBaseContext(), "Failed to obtain Location",
					Toast.LENGTH_SHORT);
		}

	}

	@Override
	protected boolean isRouteDisplayed() {

		return false;
	}

	class Touchy extends Overlay {
		@Override
		public boolean onTouchEvent(MotionEvent arg0, MapView arg1) {

			switch (arg0.getAction()) {
			case MotionEvent.ACTION_DOWN:
				start = arg0.getEventTime();
				x = (int) arg0.getX();
				y = (int) arg0.getY();
				myPoint = mvMain.getProjection().fromPixels(x, y);
				break;
			case MotionEvent.ACTION_UP:
				stop = arg0.getEventTime();
				if (stop - start > 1500) {
					Builder dialog = new AlertDialog.Builder(Maps.this);
					dialog.setTitle("Pick an option");
					dialog.setNegativeButton("Add a pin",
							new OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									CustomPinPoint pinPoints = new CustomPinPoint(
											d, Maps.this);
									OverlayItem pinPoint = new OverlayItem(
											myPoint, "Hey guys", "Whatsup");
									pinPoints.addPinPoint(pinPoint);
									overlayList.add(pinPoints);
									mvMain.invalidate();

								}

							});
					dialog.setNeutralButton("Get Address",
							new OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {

									Geocoder myCoder = new Geocoder(
											getBaseContext(), Locale
													.getDefault());
									try {
										String address = "";
										List<Address> myAddress = myCoder.getFromLocation(
												myPoint.getLatitudeE6() / 1E6,
												myPoint.getLongitudeE6() / 1E6,
												1);
										if (myAddress.size() > 0) {
											for (int i = 0; i < myAddress
													.get(0)
													.getMaxAddressLineIndex(); i++) {
												address += myAddress.get(0)
														.getAddressLine(i);
											}
											Toast result = Toast.makeText(
													getBaseContext(), address,
													Toast.LENGTH_SHORT);
											result.show();
										}

									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} finally {
										System.out.print("we made it");

									}

								}
							});
					dialog.setPositiveButton("Toggle View 3",
							new OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
								}
							});
					dialog.show();

				}
				break;
			}
			return super.onTouchEvent(arg0, arg1);
		}
	}

	public void onLocationChanged(Location location) {
		myLong = (int) (location.getLongitude() * 1E6);
		myLat = (int) (location.getLatitude() * 1E6);
		myLocation = new GeoPoint(myLat, myLong);
		CustomPinPoint pinPoints = new CustomPinPoint(d, Maps.this);
		OverlayItem pinPoint = new OverlayItem(myLocation, "Hey guys",
				"Whatsup");
		pinPoints.addPinPoint(pinPoint);
		overlayList.add(pinPoints);
		mvMain.invalidate();

	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
