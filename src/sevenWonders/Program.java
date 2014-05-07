package sevenWonders;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javafx.scene.image.Image;


public class Program {
    private static String dataFolder = "data/";
    public static URL getURL(String filepath) {
	System.out.print(new File(dataFolder+filepath)); 
	try {
	    return new File(dataFolder + filepath).toURI().toURL();
	} catch (MalformedURLException e) {
	    return null;
	}
    }
    
    private static HashMap<String, Image> loadedImages = new HashMap<String, Image>();
    public static Image getImageFromFilename(String filepath) {
	Image ret = loadedImages.get(filepath);
	if (ret == null) { 
	    ret = new Image(getURL(filepath).toString());
	    loadedImages.put(filepath, ret);
	}
	return ret;
    }
}
