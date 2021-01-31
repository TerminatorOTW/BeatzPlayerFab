
// Developed by the TerminatorOTW

package beatzplayerfab.java.layouts;

import beatzplayerfab.ResourceLoader;
import beatzplayerfab.java.api.ImageViewer;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SideMenuBar {
    
    private final VBox layout;
    public final Button homeMenu, searchMenu, playlistMenu, queueMenu, favMenu,
            settingsMenu, minimizeMenu, fullscreenMenu, exitMenu;
    public final ImageViewer fullscreen_viewer, fullscreen_exit_viewer;
    
    public SideMenuBar(String styleClassName) {
        
        final int ICON_SIZE = 24;
        
        // create layout
        this.layout = new VBox();
        this.layout.getStyleClass().add(styleClassName);
        
        // separator
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        // homeMenu
        this.homeMenu = new Button();
        this.homeMenu.getStyleClass().add("home_icon");
        homeMenu.setTooltip(new Tooltip("Home"));
        
        ImageViewer home_viewer = new ImageViewer(new ResourceLoader().loadImages("home_icon.png"));
        home_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        home_viewer.setStyle("-fx-background-color: transparent;");
        this.homeMenu.setGraphic(home_viewer);
        
        // searchMenu
        this.searchMenu = new Button();
        searchMenu.setTooltip(new Tooltip("Search"));
        
        ImageViewer search_viewer = new ImageViewer(new ResourceLoader().loadImages("search_icon.png"));
        search_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        search_viewer.setStyle("-fx-background-color: transparent;");
        this.searchMenu.setGraphic(search_viewer);
        
        // playlistMenu
        this.playlistMenu = new Button();
        playlistMenu.setTooltip(new Tooltip("Playlist"));
        
        ImageViewer playlist_viewer = new ImageViewer(new ResourceLoader().loadImages("playlist_icon.png"));
        playlist_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        playlist_viewer.setStyle("-fx-background-color: transparent;");
        this.playlistMenu.setGraphic(playlist_viewer);
        
        // queueMenu
        this.queueMenu = new Button();
        queueMenu.setTooltip(new Tooltip("Queue Music"));
        
        ImageViewer queue_viewer = new ImageViewer(new ResourceLoader().loadImages("queue_icon.png"));
        queue_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        queue_viewer.setStyle("-fx-background-color: transparent;");
        this.queueMenu.setGraphic(queue_viewer);
        
        // favMenu
        this.favMenu = new Button();
        favMenu.setTooltip(new Tooltip("Favourite Music"));
        
        ImageViewer fav_viewer = new ImageViewer(new ResourceLoader().loadImages("favourite_icon_fill.png"));
        fav_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        fav_viewer.setStyle("-fx-background-color: transparent;");
        this.favMenu.setGraphic(fav_viewer);
        
        // settingsMenu
        this.settingsMenu = new Button();
        settingsMenu.setTooltip(new Tooltip("Settings"));
        
        ImageViewer settings_viewer = new ImageViewer(new ResourceLoader().loadImages("settings_icon.png"));
        settings_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        settings_viewer.setStyle("-fx-background-color: transparent;");
        this.settingsMenu.setGraphic(settings_viewer);
        
        // minimizeMenu
        this.minimizeMenu = new Button();
        minimizeMenu.setTooltip(new Tooltip("Minimize"));
        
        ImageViewer minimize_viewer = new ImageViewer(new ResourceLoader().loadImages("minimize_icon.png"));
        minimize_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        minimize_viewer.setStyle("-fx-background-color: transparent;");
        this.minimizeMenu.setGraphic(minimize_viewer);
        
        // fullscreenMenu
        this.fullscreenMenu = new Button();
        fullscreenMenu.setTooltip(new Tooltip("Set Fullscreen"));
        
        fullscreen_viewer = new ImageViewer(new ResourceLoader().loadImages("fullscreen_icon.png"));
        fullscreen_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        fullscreen_viewer.setStyle("-fx-background-color: transparent;");
        
        fullscreen_exit_viewer = new ImageViewer(new ResourceLoader().loadImages("fullscreen_exit_icon.png"));
        fullscreen_exit_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        fullscreen_exit_viewer.setStyle("-fx-background-color: transparent;");
        
        this.fullscreenMenu.setGraphic(fullscreen_viewer);
        
        // exitMenu
        this.exitMenu = new Button();
        exitMenu.setTooltip(new Tooltip("Exit App!"));
        
        ImageViewer exit_viewer = new ImageViewer(new ResourceLoader().loadImages("close_icon.png"));
        exit_viewer.setFitSize(ICON_SIZE, ICON_SIZE);
        exit_viewer.setStyle("-fx-background-color: transparent;");
        this.exitMenu.setGraphic(exit_viewer);
        
        // adding nodes to the layout
        this.layout.getChildren().addAll(homeMenu, searchMenu, playlistMenu,
                queueMenu, favMenu, separator, settingsMenu, minimizeMenu, fullscreenMenu, exitMenu);
        
    }
    
    public VBox getLayout() {
        return this.layout;
    }
    
}
