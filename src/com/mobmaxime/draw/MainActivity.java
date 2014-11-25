package com.mobmaxime.draw;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private int STROKE_WIDTH = 8;
	LinearLayout mainlayout;
	float downx, downy, upx, upy, movex, movey;
	Canvas canvas;
	Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainlayout = (LinearLayout) findViewById(R.id.main);
		mainlayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				Log.d("HI", "OK");
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					downx = event.getX();;
					downy = event.getY();;

					break;
				case MotionEvent.ACTION_MOVE:

					movex = event.getX();
					movey = event.getY();

					canvas.drawLine(downx, downy, movex, movey, paint);
					mainlayout.invalidate();
					downx = movex;
					downy = movey;

					break;
				case MotionEvent.ACTION_UP:
					upx = event.getX();
					upy = event.getY();
					break;
				case MotionEvent.ACTION_CANCEL:
					break;
				default:
					break;
				}
				return true;
			}
		});
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		Display currentDisplay = getWindowManager().getDefaultDisplay();
		float display_width = currentDisplay.getWidth();
		float display_height = currentDisplay.getHeight();
		Bitmap bitmap = Bitmap.createBitmap((int) display_width,
				(int) display_height, Bitmap.Config.ARGB_8888);
		// canvas
		canvas = new Canvas(bitmap);
		// paint
		paint = new Paint();
		// line width
		paint.setStrokeWidth(STROKE_WIDTH);
		// set color
		paint.setColor(Color.RED);
		BitmapDrawable background = new BitmapDrawable(bitmap);

		mainlayout.setBackgroundDrawable(background);

	}
}
