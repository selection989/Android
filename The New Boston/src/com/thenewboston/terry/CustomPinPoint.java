package com.thenewboston.terry;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustomPinPoint extends ItemizedOverlay<OverlayItem> {
	private Context c;
	private final ArrayList<OverlayItem> pinPoints = new ArrayList<OverlayItem>();

	public CustomPinPoint(Drawable defaultMarker) {
		super(boundCenter(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	public CustomPinPoint(Drawable defaultMarker, Context context) {
		this(defaultMarker);
		c = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OverlayItem createItem(int i) {

		return pinPoints.get(i);
	}

	@Override
	public int size() {

		return pinPoints.size();
	}

	public void addPinPoint(OverlayItem i) {
		pinPoints.add(i);
		this.populate();
	}

	public void removeLastPinPoint() {
		pinPoints.remove(pinPoints.size() - 1);
	}

}
