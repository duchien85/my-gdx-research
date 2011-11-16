package org.gsn.caro;

import org.gsn.engine.Debug;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.AssetLoaderParameters.LoadedCallback;

public class MyLoadedCallBack implements LoadedCallback {

	@Override
	public void finishedLoading(AssetManager assetManager, String fileName, Class type) {
		// TODO Auto-generated method stub
		Debug.trace("asset", "HHAHAHAHAHHAAH");
	}

}
