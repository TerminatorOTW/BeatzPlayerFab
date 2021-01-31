
// Developed by the TerminatorOTW

package beatzplayerfab.java.controllers;

import beatzplayerfab.java.layouts.PlayerStatusBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class StatusBarController {
    
    private final MainController mainCtrl;
    private final PlayerStatusBar statusBar;
    
    private boolean statusBarMinimized = false;
    private boolean statusBarHidden = false;
    
    public StatusBarController(PlayerStatusBar psb, MainController mC) {
        this.mainCtrl = mC;
        this.statusBar = psb;
        
        // set actions
        setHideButtonAction();
        setPlayButtonAction();
        set10sNextButtonAction();
        set10sPrevButtonAction();
        setNextButtonAction();
        setPrevButtonAction();
        
    }
    
    public void changeAlbumImage(Image image) {
        statusBar.getAlbumViewer().setImage(image);
    }
    
    public Image getDefaultAlbumImage() {
        return statusBar.getImageList().get(0);
    }
    
    public void setSongName(String name) {
        statusBar.getLabelList().get(0).setText(name);
    }
    
    public void setCurrentMusicTime(String time) {
        statusBar.getLabelList().get(1).setText(time);
    }
    
    public void setTotalMusicTime(String time) {
        statusBar.getLabelList().get(2).setText(time);
    }
    
    public void setProgressValue(double value) {
        statusBar.getProgressBar().setValue(value);
    }
    
    public void setIconAsPlaying(boolean v) {
        if (v) {
            statusBar.getImageViewList().get(0).setImage(
                statusBar.getImageList().get(6)
            );
        } else {
            statusBar.getImageViewList().get(0).setImage(
                statusBar.getImageList().get(5)
            );
        }
    }
    
    public void hideAlbumView() {
        statusBar.getMainLayout().getChildren().remove(
            statusBar.getSubLayouts().get(1)
        );
        statusBar.getMainLayout().getChildren().remove(
            statusBar.getSubLayouts().get(2)
        );
        statusBar.getSubLayouts().get(3).setStyle("-fx-border-radius: 20 20 0 0; -fx-background-radius: 20 20 0 0;");
        statusBarMinimized = true;
    }
    
    public void restoreAlbumView() {
        statusBar.getMainLayout().getChildren().add(
            1, statusBar.getSubLayouts().get(1)
        );
        statusBar.getMainLayout().getChildren().add(
            2, statusBar.getSubLayouts().get(2)
        );
        statusBar.getSubLayouts().get(3).setStyle("-fx-border-radius: 0 0 0 0; -fx-background-radius: 0 0 0 0;");
        statusBarMinimized = false;
    }
    
    public void hideView() {
        statusBar.getMainLayout().getChildren().clear();
        statusBarHidden = true;
    }
    
    public void restoreView() {
        if (statusBarMinimized) {
            statusBar.getMainLayout().getChildren().addAll(
                statusBar.getSubLayouts().get(0), statusBar.getSubLayouts().get(3)
            );
        } else {
            statusBar.getMainLayout().getChildren().addAll(
                statusBar.getSubLayouts()
            );
        }
        statusBarHidden = false;
    }
    
    public boolean isStatusBarHidden() {
        return statusBarHidden;
    }
    
    public boolean isStatusBarMinimized() {
        return statusBarMinimized;
    }
    
    public VBox getStatusBarLayout() {
        return statusBar.getMainLayout();
    }
    
    public double[] getStatusBarLayoutSize() {
        return statusBar.getStatusBarSize();
    }
    
    /* private methods */
    
    private void setHideButtonAction() {
        
        // mouse movement
        statusBar.getButtonList().get(8).setOnMouseEntered(e->{
            if (!statusBarMinimized) {
                statusBar.getImageViewList().get(7).setImage(
                    statusBar.getImageList().get(13)
                );
            } else {
                statusBar.getImageViewList().get(7).setImage(
                    statusBar.getImageList().get(12)
                );
            }
        });
        
        statusBar.getButtonList().get(8).setOnMouseExited(e->{
            statusBar.getImageViewList().get(7).setImage(null);
        });

        // on fire
        statusBar.getButtonList().get(8).setOnAction(e->{
            if (statusBar.getMainLayout().getChildren().size() > 2) {
                hideAlbumView();
            } else {
                restoreAlbumView();
            }
        });
        
    }
    
    private void setPlayButtonAction() {
        
        statusBar.getButtonList().get(5).setOnAction(e->{
            mainCtrl.getMusicController().playPause();
        });
        
    }
    
    private void set10sNextButtonAction() {
        statusBar.getButtonList().get(1).setOnAction(e->{
            mainCtrl.getMusicController().skipMusic(10);
        });
    }
    
    private void set10sPrevButtonAction() {
        statusBar.getButtonList().get(2).setOnAction(e->{
            mainCtrl.getMusicController().skipMusic(-10);
        });
    }
    
    private void setNextButtonAction() {
        statusBar.getButtonList().get(3).setOnAction(e->{
            mainCtrl.getMusicController().playNext();
        });
    }
    
    private void setPrevButtonAction() {
        statusBar.getButtonList().get(4).setOnAction(e->{
            mainCtrl.getMusicController().playPrev();
        });
    }
    
}
