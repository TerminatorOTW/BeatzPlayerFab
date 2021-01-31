
// Developed by the TerminatorOTW

package beatzplayerfab.java.api;

import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ImageViewer extends Rectangle {
    
    private ImagePattern ip;
    
    public ImageViewer() {
        this(null, "image-viewer");
    }
    
    public ImageViewer(Image image) {
        this(image, "image-viewer");
    }
    
    private ImageViewer(Image image, String styleClass) {
        
        if (image != null) {
            this.ip = new ImagePattern(image);
            this.setFill(ip);
        } else {
            this.ip = null;
        }
        
        this.getStyleClass().add(styleClass);
    }
    
    public void setImage(Image image) {
        this.ip = new ImagePattern(image);
        this.setFill(ip);
    }
    
    public Image getImage() {
        if (ip != null)
            return ip.getImage();
        return null;
    }
    
    public void setFitWidth(double width) {
        this.setWidth(width);
    }
    
    public void setFitHeight(double height) {
        this.setHeight(height);
    }
    
    public double getFitWidth() {
        return this.getWidth();
    }
    
    public double getFitHeight() {
        return this.getHeight();
    }
    
    public void setFitSize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public double[] getFitSize() {
        return new double[]{this.getWidth(), this.getHeight()};
    }
    
    public void setCornerRadii(double value) {
        this.setArcHeight(value);
        this.setArcWidth(value);
    }
    
    public double getCornerRadii() {
        return this.getArcWidth();
    }
    
}
