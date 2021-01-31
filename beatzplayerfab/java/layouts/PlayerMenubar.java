
// Developed by the TerminatorOTW

package beatzplayerfab.java.layouts;

import java.io.File;
import java.net.MalformedURLException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayerMenubar {
    
    private MenuBar menuBar;
    
    private final Stage window;
    
    private Menu fileMenu, audioMenu, toolsMenu, viewMenu, helpMenu;
    
    // sub menus
    private Menu statusBar;
    
    private MenuItem openFile, openFolder, minimize, minimizeButton, hideButton;
    
    public PlayerMenubar(Stage stage) {
        this.window = stage;
        setMenuBar();
    }
    
    public MenuBar getLayout() {
        return menuBar;
    }
    
    private void setMenuBar() {
        
        // MenuBar
        menuBar = new MenuBar();
        menuBar.getStyleClass().add("menuBar");
        
            // Menus
            setFileMenu();
            setAudioMenu();
            setToolsMenu();
            setViewMenu();
            setHelpMenu();
        
        // adding menus to menuBar
        menuBar.getMenus().addAll(
            fileMenu, viewMenu, audioMenu, toolsMenu, helpMenu
        );
        
    }
    
    private void setFileMenu() {
        
        // Menus
        fileMenu = new Menu("File");

            // items
            openFile = new MenuItem("Open File...");
            openFile.setAccelerator(KeyCombination.valueOf("Ctrl+O"));

            openFolder = new MenuItem("Open Folder...");
            openFolder.setAccelerator(KeyCombination.valueOf("Ctrl+Shift+O"));
            
            minimize = new MenuItem("Minimize");
            minimize.setAccelerator(KeyCombination.valueOf("Ctrl+M"));
            
            minimize.setOnAction(e->{
                window.setIconified(true);
            });
            
            MenuItem exit = new MenuItem("Exit");
            exit.setAccelerator(KeyCombination.valueOf("F2"));

            exit.setOnAction(e->{
                window.close();
            });

            fileMenu.getItems().addAll(openFile,openFolder,minimize,exit);
    }
    
    private void setAudioMenu() {
        
        // Menu
        audioMenu = new Menu("Audio");
        
    }
    
    private void setToolsMenu() {
        
        // Menu
        toolsMenu = new Menu("Tools");
        
    }
    
    private void setViewMenu() {
    
        // Menu
        viewMenu = new Menu("View");
            
            // menuItems
            /*
            minimize.setOnAction(e->{

                if (!minimized) {
                    minimized = true;

                    mainLayout.setTop(null);
                    mainLayout.setCenter(getMinimizedLayout(window));
                    mainLayout.setLeft(null);
                    mainLayout.setRight(null);
                    mainLayout.setBottom(null);

                    mainLayout.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

                    Timeline timeLine = new Timeline();
                    timeLine.setCycleCount(Integer.MAX_VALUE);
                    timeLine.setAutoReverse(true);

                    KeyFrame kf = new KeyFrame(Duration.millis(10), f->{
                        if (minimized_view.getRotate() == 360)
                            minimized_view.setRotate(0);
                        else
                            minimized_view.setRotate(minimized_view.getRotate() + 5);

                        if (!minimized)
                            timeLine.stop();
                    });

                    timeLine.getKeyFrames().add(kf);
                    timeLine.play();
                }
            });*/
            
            statusBar = new Menu("Status Bar");
            
                // items
                minimizeButton = new MenuItem("Minimize");
                minimizeButton.setAccelerator(KeyCombination.valueOf("Ctrl+Shift+M"));
                
                hideButton = new MenuItem("Hide");
                hideButton.setAccelerator(KeyCombination.valueOf("Ctrl+Shift+H"));
                
                statusBar.getItems().addAll(minimizeButton, hideButton);

            viewMenu.getItems().addAll(statusBar);
    }
    
    private void setHelpMenu() {
        
        // Menu
        helpMenu = new Menu("Help");
        
    }
    
    /* control functions */
    public void fireOpenFileItem() {
        openFile.fire();
    }
    public void setOpenFileAction(EventHandler<ActionEvent> event) {
        openFile.setOnAction(event);
    }
    
    public void fireOpenFolderItem() {
        openFolder.fire();
    }
    public void setOpenFolderAction(EventHandler<ActionEvent> event) {
        openFolder.setOnAction(event);
    }
    
    public void fireMinimizeItem() {
        minimize.fire();
    }
    public void setMinimizeAction(EventHandler<ActionEvent> event) {
        minimize.setOnAction(event);
    }
    
    public void fireStatusBarHide() {
        hideButton.fire();
    }
    public void setStatusBarHideAction(EventHandler<ActionEvent> event) {
        hideButton.setOnAction(event);
    }
    public void setStatusBarHideItemName(String name) {
        hideButton.setText(name);
    }
    
    public void fireStatusBarMinimize() {
        minimizeButton.fire();
    }
    public void disableStatusBarMinimize(boolean v) {
        minimizeButton.setDisable(v);
    }
    public void setStatusBarMinimizeAction(EventHandler<ActionEvent> event) {
        minimizeButton.setOnAction(event);
    }
    public void setStatusBarMinimizeItemName(String name) {
        minimizeButton.setText(name);
    }
    
    /* -- end -- */
    
}
