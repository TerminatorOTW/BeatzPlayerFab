
// Developed by the TerminatorOTW

package beatzplayerfab.java.controllers;

import beatzplayerfab.java.layouts.SideMenuBar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SideMenuCtrl {

    private final MainController mainCtrl;
    private final SideMenuBar bar;
    private int x = 1;
    
    public SideMenuCtrl(SideMenuBar bar, MainController mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.bar = bar;
        
        /**
         * system actions
         */
        this.bar.minimizeMenu.setOnAction(e->{
            this.mainCtrl.getStage().setIconified(true);
        });
        this.bar.fullscreenMenu.setOnAction(e->{
            if (this.mainCtrl.getStage().isFullScreen()) {
                this.mainCtrl.getStage().setFullScreen(false);
                this.bar.fullscreenMenu.setGraphic(this.bar.fullscreen_viewer);
            } else {
                this.mainCtrl.getStage().setFullScreen(true);
                this.bar.fullscreenMenu.setGraphic(this.bar.fullscreen_exit_viewer);
            }
        });
        this.bar.exitMenu.setOnAction(e->{
            this.mainCtrl.getStage().close();
        });
    }
    
}
