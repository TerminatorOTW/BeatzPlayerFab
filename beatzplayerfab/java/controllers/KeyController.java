
// Developed by the TerminatorOTW

package beatzplayerfab.java.controllers;

import javafx.application.Platform;
import javafx.scene.Scene;

public class KeyController {
    
    private final MainController mainCtrl;
    
    public KeyController(MainController m) {
        this.mainCtrl = m;
        
        Platform.runLater(()->{
            m.getMainLayout().getLayout().getScene().setOnKeyReleased(e->{
                switch(e.getCode()) {
                    case K:
                        m.getMusicController().playPause();
                        break;
                    case LEFT:
                        m.getMusicController().playPrev();
                        break;
                    case RIGHT:
                        m.getMusicController().playNext();
                        break;
                }
            });
        });
        
    }
    
}
