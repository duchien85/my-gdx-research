package com.gsn;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class CaroAndroidActivity extends AndroidApplication {
    /** Called when the activity is first created. */
	@Override
	public void onCreate (Bundle savedInstanceState) {
		Log.i("APP", "on Create");
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		initialize(new CaroGame(), config);				
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.i("APP", "on Pause");
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i("APP", "on Resume");
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("APP", "on Destroy");
		super.onDestroy();
	}
}