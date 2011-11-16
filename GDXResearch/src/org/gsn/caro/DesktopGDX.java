package org.gsn.caro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.imageio.ImageIO;

import org.gsn.engine.Debug;
import org.gsn.engine.GsnServerInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;


public class DesktopGDX {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		new JoglApplication(new CaroGame(), "Caro", Constant.WIDTH, Constant.HEIGHT, false);
//		String s = "http://myplay.apps.zing.vn/caro/service.php?act=getServerInfo";
//		List<GsnServerInfo> list = GsnServerInfo.GsnServerInfo(s);
//		Debug.trace(list.get(0).getIp());
	}
}

