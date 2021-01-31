
// Developed by the TerminatorOTW

package beatzplayerfab;

import java.io.File;
import java.net.URL;
import javafx.scene.image.Image;

public class ResourceLoader {
    
    public URL loadStylesheet(String name) {
        
        URL filePath = null;
        
        try {
            filePath = getClass().getResource(
                "resources/css/" + name
            );
        } catch (Exception e) {
            System.out.println("Error!");;
        }
        
        return filePath;
    }
    
    public Image loadImages(String name) {
        
        Image image = null;
        
        try {
            image = new Image(getClass().getResourceAsStream(
                "resources/images/" + name
            ));
        } catch (Exception e) {}
        
        return image;
    }
    
}
