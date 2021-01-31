
// Developed by the TerminatorOTW

package beatzplayerfab.java.api;

import javafx.scene.Cursor;
import javafx.scene.Node;

public class NodeMover {
    
    private double dx = 0;
    private double dy = 0;
    
    public void setOnNode(Node node) {
        
        node.setOnMousePressed(e->{
            this.dx = e.getSceneX();
            this.dy = e.getSceneY();
        });
        
        node.setOnMouseDragged(e->{
            node.getScene().getWindow().setX(e.getScreenX() - dx);
            node.getScene().getWindow().setY(e.getScreenY() - dy);
        });
        
    }
    
}
