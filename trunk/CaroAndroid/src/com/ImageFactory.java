package engine.gdx;

import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageFactory {
	public static interface IImageFactoryListener {
		void onFinishSaving();

		void onError(Exception e);
	}

	public static void saveBitmapToFileAsync(final String link, final int width, final int height, final int quality, final OutputStream out, final IImageFactoryListener listener) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				URL url;
				URLConnection uCon;
				Bitmap bMap;
				Bitmap des;
				try {
					url = new URL(link);
					uCon = url.openConnection();
					bMap = BitmapFactory.decodeStream(uCon.getInputStream());
					des = Bitmap.createScaledBitmap(bMap, width, height, true);
					des.compress(Bitmap.CompressFormat.PNG, quality, out);
					out.flush();
					out.close();
					listener.onFinishSaving();
				} catch (Exception e) {
					Log.e("Gallery", "loi gi nay", e);
					listener.onError(e);
				}
			}
		});
		t.start();
	}

	public static void saveBitmapToFile(String link, int width, int height, int quality, OutputStream out) {
		URL url;
		URLConnection uCon;
		Bitmap bMap;
		Bitmap des;
		try {
			url = new URL(link);
			uCon = url.openConnection();
			bMap = BitmapFactory.decodeStream(uCon.getInputStream());
			des = Bitmap.createScaledBitmap(bMap, width, height, true);
			des.compress(Bitmap.CompressFormat.PNG, quality, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			Log.e("Gallery", "loi gi nay", e);
		}
	}

	public String getFileName(String path) {
		int slashIndex = path.lastIndexOf('/');
		String fileName = path.substring(slashIndex + 1);
		return fileName;
	}	
}
