
// Developed by the TerminatorOTW

package beatzplayerfab.java.controllers;

import beatzplayerfab.java.functions.PrefSave;
import beatzplayerfab.java.layouts.PlayerMenubar;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MenuController {
    
    private final PlayerMenubar menuBar;
    private final MainController mainCtrl;
    
    private final FileChooser fileChooser;
    private final DirectoryChooser directoryChooser;
    
    private final PrefSave prefSave = new PrefSave();
    
    public MenuController(PlayerMenubar m, MainController mC) {
        this.menuBar = m;
        this.mainCtrl = mC;
        
        // setting the fileChooser
        this.fileChooser = new FileChooser();
        this.fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(
            "Music File", "*.mp3"
        ));
        
        // setting the directory chooser
        this.directoryChooser = new DirectoryChooser();
        
        setActions();
    }
    
    private void setActions() {
        menuBar.setOpenFileAction(getOpenFileEvent());
        menuBar.setOpenFolderAction(getOpenFolderEvent());
        menuBar.setStatusBarHideAction(getStatusHideEvent());
        menuBar.setStatusBarMinimizeAction(getStatusMinimizeEvent());
    }
    
    private EventHandler<ActionEvent> getOpenFileEvent() {
        
        EventHandler<ActionEvent> event = e -> {
            
            String v = prefSave.getSavedValue("docsData", "File", "lastDir");
            File f = new File(v);

            if (v.isEmpty() || !f.isDirectory()) {
                this.fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
                );
            } else {
                this.fileChooser.setInitialDirectory(f);
            }
            
            File file = fileChooser.showOpenDialog(mainCtrl.getStage());
            
            if (file != null) {
                URL currentAudioUrl = null;
                try {
                    currentAudioUrl = file.toURI().toURL();
                } catch (MalformedURLException ex) {}
                
                mainCtrl.getMusicController().addMusic(currentAudioUrl, file.getName());
                
                prefSave.setSavedValue("docsData", "File", "lastDir", file.getParent());
            }
        };
                
        return event;
    }
    
    private EventHandler<ActionEvent> getOpenFolderEvent() {
        
        EventHandler<ActionEvent> event = e -> {
            
            String v = prefSave.getSavedValue("docsData", "File", "lastDir");
            File f = new File(v);

            if (v.isEmpty() || !f.isDirectory()) {
                this.directoryChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
                );
            } else {
                this.directoryChooser.setInitialDirectory(f);
            }
            
            File file = directoryChooser.showDialog(mainCtrl.getStage());
            
            if (file != null && file.isDirectory()) {
                File[] fileList = null;
                try {
                    fileList = file.listFiles((File dir, String name) -> 
                        name.toLowerCase().endsWith(".mp3")
                    );
                } catch (Exception g) {}
                
                if (fileList != null) {
                    mainCtrl.getMusicController().addMusic(fileList);
                    prefSave.setSavedValue("docsData", "File", "lastDir", file.getPath());
                }
            }
            
        };
        
        return event;
    }
    
    private EventHandler<ActionEvent> getStatusMinimizeEvent() {
        
        EventHandler<ActionEvent> event = e -> {
            if (mainCtrl.getStatusBarController().isStatusBarMinimized()) {
                mainCtrl.getStatusBarController().restoreAlbumView();
                menuBar.setStatusBarMinimizeItemName("Minimize");
            } else {
                mainCtrl.getStatusBarController().hideAlbumView();
                menuBar.setStatusBarMinimizeItemName("Maximize");
            }
        };
     
        return event;
    }
    
    private EventHandler<ActionEvent> getStatusHideEvent() {
        
        EventHandler<ActionEvent> event = e -> {
            if (mainCtrl.getStatusBarController().isStatusBarHidden()) {
                mainCtrl.getStatusBarController().restoreView();
                menuBar.setStatusBarHideItemName("Hide");
                menuBar.disableStatusBarMinimize(false);
            } else {
                mainCtrl.getStatusBarController().hideView();
                menuBar.setStatusBarHideItemName("Show");
                menuBar.disableStatusBarMinimize(true);
            }
        };
        
        return event;
    }
    
}
