
// Developed by the TerminatorOTW

package beatzplayerfab.java.layouts;

import beatzplayerfab.ResourceLoader;
import beatzplayerfab.java.controllers.MainController;
import beatzplayerfab.java.controllers.MusicController;
import beatzplayerfab.java.player.MusicPlayer;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainLayout {
    
    private final BorderPane mainLayout;
    
    private final ResourceLoader resLoader = new ResourceLoader();
    private final MusicPlayer musicPlayer = new MusicPlayer();
    /*
    private URL currentAudioUrl = null;
    private MediaPlayer currentMedia = null;
    private boolean isPlaying = false;
    private boolean songCompleted = false;
    private boolean valueChangingBOM = false;
    private boolean minimized = false;*/
    
    /* Layout classes */
    private final PlayerStatusBar playerStatusBar;
    private final PlayerMenubar menuBar;
    private final TopBar topBar;
    private final SideMenuBar sideMenu;
    
    /* Controller Classes */
    private final MainController mainController;
    
    private Stage window;
    
    public MainLayout(Stage window, double aw, double ah) {
        
        // copying the stage
        this.window = window;
        
        // Setting the borderPane layout
        this.mainLayout = new BorderPane();
        this.mainLayout.setPrefWidth(aw);
        this.mainLayout.setPrefHeight(ah);
        this.mainLayout.getStyleClass().add("mainLayout");
        this.mainLayout.getStylesheets().add(
            resLoader.loadStylesheet("myStyle.css").toExternalForm()
        );
        
            // setting contents of the mainLayout
            menuBar = new PlayerMenubar(window);
            this.mainLayout.setTop(menuBar.getLayout());
            
            topBar = new TopBar("Beatz Player V1.0", "top_bar");
            //this.mainLayout.setTop(topBar.getLayout());
            
            sideMenu = new SideMenuBar("side_menu");
            this.mainLayout.setLeft(sideMenu.getLayout());
            
            playerStatusBar = new PlayerStatusBar(aw);
            this.mainLayout.setBottom(playerStatusBar.getLayout());
            
            // setting controllers
            mainController = new MainController(this);
            
    }
    
    public BorderPane getLayout() {
        return mainLayout;
    }
    
    public MusicPlayer getPlayer() {
        return musicPlayer;
    }
    
    public Stage getWindow() {
        return window;
    }
    
    public PlayerMenubar getMenuBar() {
        return menuBar;
    }
    
    public PlayerStatusBar getStatusBar() {
        return playerStatusBar;
    }
    
    public SideMenuBar getSideMenu() {
        return sideMenu;
    }
    
    public void clearMainLayout() {
        mainLayout.setTop(null);
        mainLayout.setCenter(null);
        mainLayout.setBottom(null);
        mainLayout.setLeft(null);
        mainLayout.setRight(null);
    }
    
    public void restoreMainLayout() {
        mainLayout.setTop(menuBar.getLayout());
        mainLayout.setBottom(playerStatusBar.getLayout());
    }
    
}
