
// Developed by the TerminatorOTW

package beatzplayerfab.java.layouts;

import beatzplayerfab.ResourceLoader;
import beatzplayerfab.java.api.ImageViewer;
import beatzplayerfab.java.api.NodeMover;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class TopBar {
    
    private final HBox topBar;
    private final Label appNameLabel;
    private final Button menu;
    
    private int dX = 0;
    private int dY = 0;
    
    public TopBar(String appName, String styleClassName) {
        
        // creating layout
        this.topBar = new HBox();
        topBar.getStyleClass().add(styleClassName);
        
        // menu
        this.menu = new Button();
        this.menu.getStyleClass().add("menu_icon");
        menu.setTooltip(new Tooltip("Menu"));
        
        ImageViewer menu_viewer = new ImageViewer(new ResourceLoader().loadImages("menu_icon.png"));
        menu_viewer.setFitSize(24, 24);
        menu_viewer.setStyle("-fx-background-color: transparent;");
        this.menu.setGraphic(menu_viewer);
        
        // app name label
        this.appNameLabel = new Label(appName);
        
        // adding items to the layout
        topBar.getChildren().addAll(menu, appNameLabel);
        
        NodeMover nodeMover = new NodeMover();
        nodeMover.setOnNode(topBar);
    }
    
    public HBox getLayout() {
        return topBar;
    }
    
}
