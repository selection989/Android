package com.thenewboston.terry;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLExample extends Activity {

	GLSurfaceView ourSurface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ourSurface = new GLSurfaceView(this);
		ourSurface.setRenderer(new GLRenderer());
		setContentView(ourSurface);
	}

	@Override
	protected void onResume() {

		super.onResume();
		ourSurface.onResume();
	}

	@Override
	protected void onPause() {

		super.onPause();
		ourSurface.onPause();
	}

}
