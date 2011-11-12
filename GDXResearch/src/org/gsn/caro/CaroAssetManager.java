package org.gsn.caro;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CaroAssetManager extends AssetManager implements AssetErrorListener {	
	private final String pack_url = "gdx/pack";
		
	public synchronized void loadAll(){		
		Debug.trace("load all");		
		
		load("gdx/img1.png", Texture.class);
		load(pack_url, TextureAtlas.class);
	}
		
	public void finishLoadingPack(String pack) {
		// TODO Auto-generated method stub
		while (!isLoaded(pack)){
			update();
		}
	}

	
	public synchronized void unloadAll() {
		// TODO Auto-generated method stub
		Debug.trace("unnnload all");
		unload("gdx/img1.png");
		unload(pack_url);
	}
	
	private CaroAssetManager(){
		super();
	}	
	
	private static CaroAssetManager _instance = null;
	public static CaroAssetManager getInstance(){
//		if (_instance == null)
//			_instance = new CaroAssetManager();
		return _instance;
	}
	
	public static CaroAssetManager newInstance(){		
		_instance = new CaroAssetManager();
		return _instance;
	}
	
	public void create(){
		Gdx.app.setLogLevel(Application.LOG_ERROR);
		Resolution[] resolutions = { new Resolution(320, 480, ".320480"), new Resolution(480, 800, ".480800"), new Resolution(480, 856, ".480854") };
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		
		setLoader(Texture.class, new TextureLoader(resolver));
		setErrorListener(this);
		loadAll();
		Texture.setAssetManager(this);		
	}
	
	@Override
	public void error(String fileName, Class type, Throwable throwable) {
		// TODO Auto-generated method stub
		Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception) throwable);
	}
		
	
	public TextureRegion getAvatarRegion(){
		if (isLoaded(pack_url))
			return get(pack_url, TextureAtlas.class).findRegion("avatar");
		else {
			//Debug.trace("unloaded ne");
			return null;
		}
	}
}
