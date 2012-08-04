package com.thenewboston.terry;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements OnClickListener {

	Button bTakePicture;
	ImageButton bSetWallPaper;
	ImageView ivPicture;
	final static int cameraResult = 0;
	Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		initialize();
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);
	}

	private void initialize() {
		bTakePicture = (Button) findViewById(R.id.bTakePicture);
		bSetWallPaper = (ImageButton) findViewById(R.id.ibSetBackGround);
		ivPicture = (ImageView) findViewById(R.id.ivPicture);
		bTakePicture.setOnClickListener(this);
		ivPicture.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.bTakePicture):
			Intent cameraIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, cameraResult);
			break;
		case (R.id.ibSetBackGround):
			break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			ivPicture.setImageBitmap(bmp);

		}
	}

}
