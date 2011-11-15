package org.gsn.caro;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CaroAssetManager extends AssetManager implements AssetErrorListener {	
	public static final String pack_url = "gdx/pack";
	public static final String img1 = "gdx/img1.png";
		
	public synchronized void loadAll(){		
		Debug.trace("load all");		
		AssetLoaderParameters<TextureAtlas> params = new AssetLoaderParameters<TextureAtlas>();
		params.loadedCallback = new AssetLoaderParameters.LoadedCallback() {

			@Override
			public void finishedLoading(AssetManager assetManager, String fileName, Class type) {
				// TODO Auto-generated method stub
				Debug.trace("HAAAAAAAAAAAAAA");
			}
		};
		AssetDescriptor as = new AssetDescriptor(pack_url, TextureAtlas.class, params);		
		load(as);
	}
		
//	
//	@Override
//	public synchronized boolean update() {
//		// TODO Auto-generated method stub
//		//Debug.trace("--------updating");
//		return super.update();
//	}
	
	@Override
	public synchronized <T> void load(String fileName, Class<T> type) {
		// TODO Auto-generated method stub
		Debug.trace("--------loading : " + fileName);
		super.load(fileName, type);
	}
	
	@Override
	public synchronized void unload(String fileName) {
		// TODO Auto-generated method stub
		Debug.trace("--------unloading : " + fileName);
		if (super.isLoaded(fileName))
			super.unload(fileName);
	}	

	
	public synchronized void unloadAll() {
		// TODO Auto-generated method stub
		Debug.trace("unnnload all");		
		unload(pack_url);
	}
	
	private CaroAssetManager(){
		super();
		create();
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
		
	public void finishLoadingPack(String name) {
		// TODO Auto-generated method stub
			
		while (!isLoaded(name))		
		{
			update();			
			Thread.yield();
		}					
		Debug.trace("Log ", " Texture loaded: " + isLoaded(name));
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
