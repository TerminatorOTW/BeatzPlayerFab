
// Developed by the TerminatorOTW

package beatzplayerfab.java.controllers;

import beatzplayerfab.java.layouts.MainLayout;
import beatzplayerfab.java.layouts.PlayerMenubar;
import beatzplayerfab.java.layouts.PlayerStatusBar;
import beatzplayerfab.java.player.MusicPlayer;
import javafx.stage.Stage;

public class MainController {
    
    private final MainLayout mainLayout;
    
    private final MusicController musicController;
    private final KeyController keyController;
    private final MenuController menuController;
    private final StatusBarController statusBarController;
    private final SideMenuCtrl sideMenuCtrl;
    
    private final Stage stage;
    
    public MainController (MainLayout mainL) {
        
        this.stage = mainL.getWindow();
        this.mainLayout = mainL;
        this.musicController = new MusicController(mainL.getPlayer(), this);
        this.keyController = new KeyController(this);
        this.menuController = new MenuController(mainL.getMenuBar(), this);
        this.statusBarController = new StatusBarController(mainL.getStatusBar(), this);
        this.sideMenuCtrl = new SideMenuCtrl(mainL.getSideMenu(), this);
        
    }
    
    public MainLayout getMainLayout() {
        return mainLayout;
    }
    
    public MusicController getMusicController() {
        return musicController;
    }
    
    public KeyController getKeyController() {
        return keyController;
    }
    
    public MenuController getMenuController() {
        return menuController;
    }
    
    public StatusBarController getStatusBarController() {
        return statusBarController;
    }
    
    public SideMenuCtrl getSideMenuCtrl() {
        return sideMenuCtrl;
    }
    
    public Stage getStage() {
        return stage;
    }
    
}
