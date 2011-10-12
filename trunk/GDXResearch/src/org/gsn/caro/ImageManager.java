package org.gsn.caro;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
 


 
public class ImageManager {
 
        //private final String  PATH = "/data/data/com.helloandroid.imagedownloader/";  //put the downloaded file here
      
 
	public static void DownloadFromUrl(String imageURL, OutputStream fos) { // this
																			// is
																			// the
																			// downloader
																			// method
		try {
			URL url = new URL(imageURL); // you can write here any link	

			long startTime = System.currentTimeMillis();

			/* Open a connection to that URL. */
			URLConnection ucon = url.openConnection();

			/*
			 * Define InputStreams to read from the URLConnection.
			 */
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);

			/*
			 * Read bytes to the Buffer until there is nothing more to read(-1).
			 */
			byte[] b = new byte[1024];
			int current = 0;			
			while ((current = bis.read(b)) != -1) {
				fos.write(b, 0, current);
			}
			/* Convert the Bytes read to a String. */
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
     
}