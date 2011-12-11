package com.gsn.asset;

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
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.gdx.Debug;

public class CaroAssetManager extends AssetManager implements AssetErrorListener {	
	public static final String pack_url = "gdx/pack";
	public AtlasRegion avatar;
	
	
	public void assignContent(){
		TextureAtlas atlas = get(pack_url, TextureAtlas.class);
		avatar = atlas.findRegion("avatar"); 
	}
	
	public synchronized void loadAll(){					
		//TextureLoader.TextureParameter params = new com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter();		
		AssetDescriptor as = new AssetDescriptor(pack_url, TextureAtlas.class);		
		load(as);		
	}
		
	
	@Override
	public synchronized boolean update() {
		// TODO Auto-generated method stub
		//Debug.trace("--------updating");
		return super.update();
	}
	
	@Override
	public synchronized <T> void load(String fileName, Class<T> type) {
		// TODO Auto-generated method stub
		Debug.trace("asset", "loading 2: " + fileName);
		super.load(fileName, type);
	}
	
	@Override
	public synchronized void load(AssetDescriptor desc) {
		// TODO Auto-generated method stub
		Debug.trace("asset", "loading 1: " + desc.fileName);
		super.load(desc);
	}
	
	@Override
	public synchronized <T> void load(String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
		// TODO Auto-generated method stub
		Debug.trace("asset", "loading 3: " + fileName);
		super.load(fileName, type, parameter);
	}
	
	@Override
	public synchronized void unload(String fileName) {
		// TODO Auto-generated method stub
		Debug.trace("asset", "unloading : " + fileName);	
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
		return _instance;
	}
	
	public static CaroAssetManager newInstance(){		
		_instance = new CaroAssetManager();
		return _instance;
	}
	
	@Override
	public void finishLoading() {
		// TODO Auto-generated method stub
		while (!update()){
			//Debug.trace("update");
			Thread.yield();
		}
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

}
