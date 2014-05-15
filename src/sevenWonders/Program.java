package sevenWonders;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.scene.image.Image;

public class Program {
    // Prevent instantiation of this class.
    private Program() {}
    private final static String dataFolder = "data/";

    /**
     * Attempts to load the file path by resource first. If this fails then it
     * falls back to direct file path instead.
     */
    public static URL getURL(String filepath) {
	URL resource = Program.class.getResource("/" + filepath);
	try {
	    if (resource != null && new File(resource.toURI()).exists()) {
		return resource;
	    }
	} catch (URISyntaxException e) {
	    // Do nothing.
	}
	System.out.print(new File(dataFolder + filepath));
	try {
	    return new File(dataFolder + filepath).toURI().toURL();
	} catch (MalformedURLException e) {
	    return null;
	}
    }

    private final static Map<String, Image> loadedImages = new ConcurrentHashMap<String, Image>();

    public static Image getImageFromFilename(String filepath) {
	Image ret = loadedImages.get(filepath);
	if (ret == null) {
	    ret = new Image(getURL(filepath).toString());
	    loadedImages.put(filepath, ret);
	}
	return ret;
    }
    
    public static void loadCardImages(int players) {
	int amountOfCards = players * 7;
	for (int era = 1; era <= 3; era++) {
	    if (era == 3) {
		amountOfCards -= players + 2;
	    }
	    
	    for (int card = 1; card <= amountOfCards; card++) {
		getImageFromFilename("era" + era + " - " + card + ".png");
	    }
	}
    }
}
