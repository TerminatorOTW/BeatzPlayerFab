
// Developed by the TerminatorOTW

package beatzplayerfab.java.layouts;

import beatzplayerfab.ResourceLoader;
import beatzplayerfab.java.api.ImageViewer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class PlayerStatusBar {
    
    private VBox mainBar;
    private HBox hideOpenBar, aboutBar, progressLayout, playBar;
    
    private final double APP_WIDTH;
    
    private double statusBarHeight = 0;
    private double statusBarWidth = 0;
    
    private final ResourceLoader resLoader = new ResourceLoader();
    
    private ImageViewer album_view;
    
    private ImageView playPause_view, forward_view,
            backward_view, skipBackward_view, skipForward_view, volume_view, favourite_view, iv;
    
    private Button album_button, skipForward, skipBackward,
            forward_button, backward_button, playPause_button, favourite_button, volume_button, hideOpen_button;
    
    private Label song_name, currentTime, totalTime;
    
    private Slider progress_bar;
        
    private final Image album_art = resLoader.loadImages("album_icon.png");
    private final Image forward = resLoader.loadImages("next_icon.png");
    private final Image backward = resLoader.loadImages("previous_icon.png");
    private final Image pause = resLoader.loadImages("music_pause.png");
    private final Image play = resLoader.loadImages("music_play.png");
    private final Image playIcon = resLoader.loadImages("play_icon.png");
    private final Image pauseIcon = resLoader.loadImages("pause_icon.png");
    private final Image skipBackwardIcon = resLoader.loadImages("ten_sec_back.png");
    private final Image skipForwardIcon = resLoader.loadImages("ten_sec_next.png");
    private final Image favouriteIconBorder = resLoader.loadImages("favourite_icon_border.png");
    private final Image favouriteIconFill = resLoader.loadImages("favourite_icon_fill.png");
    private final Image volumeIcon = resLoader.loadImages("volume_up_icon.png");
    private final Image openHiddenIcon = resLoader.loadImages("open_hidden.png");
    private final Image hideIcon = resLoader.loadImages("hide.png");
    
    private boolean statusBarMinimized = false;
    
    // arrays
    List<HBox> subLayouts = new ArrayList<>();
    List<Image> imageList = new ArrayList<>();
    List<ImageView> imageViewList = new ArrayList<>();
    List<Button> buttonList = new ArrayList<>();
    List<Label> labelList = new ArrayList<>();
    
    public PlayerStatusBar(double app_width) {
        this.APP_WIDTH = app_width;
        
        Collections.addAll(imageList,
            album_art, forward, backward, pause, play, playIcon, pauseIcon, skipBackwardIcon,
            skipForwardIcon, favouriteIconBorder, favouriteIconFill, volumeIcon, openHiddenIcon, hideIcon
        );
        
        setLayout();
    }
    
    public VBox getLayout() {
        return mainBar;
    }
    
    private void setLayout() {
        
        // setLayouts
        setImageViews();
        setButtonsAndLabels();
        setProgressBar();
        
        // playbar
        hideOpenBar = new HBox();
        hideOpenBar.setAlignment(Pos.CENTER);
        hideOpenBar.getStyleClass().add("hideOpenBar");
        
            // item
            iv = new ImageView();
            iv.setImage(null);
            iv.setFitHeight(16);
            iv.setFitWidth(16);
            
            hideOpen_button = new Button();
            hideOpen_button.setGraphic(iv);
            
            hideOpenBar.getChildren().add(hideOpen_button);
        
        aboutBar = new HBox();
        aboutBar.setAlignment(Pos.CENTER_LEFT);
        aboutBar.getStyleClass().add("aboutBar");
        
        progressLayout = new HBox();
        progressLayout.setAlignment(Pos.CENTER);
        progressLayout.getStyleClass().add("progressLayout");
        
        playBar = new HBox();
        playBar.setAlignment(Pos.CENTER);
        playBar.getStyleClass().add("playBar");
        
        // adding nodes to the bars
        aboutBar.getChildren().addAll(
            album_button, song_name
        );
        
        progressLayout.getChildren().addAll(
            currentTime, progress_bar, totalTime
        );
        
        playBar.getChildren().addAll(
            favourite_button, backward_button, skipBackward, playPause_button, skipForward, forward_button, volume_button
        );
        
        Collections.addAll(subLayouts,
            aboutBar, progressLayout, playBar
        );
        
        Collections.addAll(imageViewList,
            playPause_view, forward_view, backward_view, skipBackward_view,
            skipForward_view, volume_view, favourite_view, iv
        );
        
        Collections.addAll(buttonList,
            album_button, skipForward, skipBackward, forward_button, backward_button,
            playPause_button, favourite_button, volume_button, hideOpen_button
        );
        
        Collections.addAll(labelList,
            song_name, currentTime, totalTime
        );
        
        // mainBar
        mainBar = new VBox();
        mainBar.setFillWidth(true);
        mainBar.getStyleClass().add("mainBar");
        mainBar.getChildren().addAll(subLayouts);
        
        mainBar.widthProperty().addListener(e->{
            statusBarWidth = mainBar.getWidth();
        });
        mainBar.heightProperty().addListener(e->{
            statusBarHeight = mainBar.getHeight();
        });
    }
    
    private void setImageViews() {
        
        album_view = new ImageViewer();
        album_view.setImage(album_art);
        album_view.setFitSize(48, 48);
        album_view.setArcHeight(48);
        album_view.setArcWidth(48);
        
        forward_view = new ImageView();
        forward_view.setImage(forward);
        forward_view.setPreserveRatio(true);
        forward_view.setFitHeight(42);
        forward_view.setFitWidth(42);
        
        playPause_view = new ImageView();
        playPause_view.setPreserveRatio(true);
        playPause_view.setFitHeight(48);
        playPause_view.setFitWidth(48);
        
        backward_view = new ImageView();
        backward_view.setImage(backward);
        backward_view.setPreserveRatio(true);
        backward_view.setFitHeight(42);
        backward_view.setFitWidth(42);
        
        skipForward_view = new ImageView();
        skipForward_view.setImage(skipForwardIcon);
        skipForward_view.setPreserveRatio(true);
        skipForward_view.setFitHeight(36);
        skipForward_view.setFitWidth(36);
        
        skipBackward_view = new ImageView();
        skipBackward_view.setImage(skipBackwardIcon);
        skipBackward_view.setPreserveRatio(true);
        skipBackward_view.setFitHeight(36);
        skipBackward_view.setFitWidth(36);
        
        favourite_view = new ImageView();
        favourite_view.setImage(favouriteIconBorder);
        favourite_view.setPreserveRatio(true);
        favourite_view.setFitHeight(28);
        favourite_view.setFitWidth(28);
        
        volume_view = new ImageView();
        volume_view.setImage(volumeIcon);
        volume_view.setPreserveRatio(true);
        volume_view.setFitHeight(28);
        volume_view.setFitWidth(28);
        
    }
    
    private void setButtonsAndLabels() {
        
        album_button = new Button();
        album_button.setGraphic(album_view);
        
        song_name = new Label();
        song_name.setText("Song : Unknown\nArtist : Unknown");
        
        skipForward = new Button();
        skipForward.setGraphic(skipForward_view);
        
        skipBackward = new Button();
        skipBackward.setGraphic(skipBackward_view);
        
        forward_button = new Button();
        forward_button.setGraphic(forward_view);
        forward_button.setTooltip(new Tooltip("Play Next"));
        
        backward_button = new Button();
        backward_button.setGraphic(backward_view);
        backward_button.setTooltip(new Tooltip("Play Previous"));
        
        playPause_button = new Button();
        playPause_button.setTooltip(new Tooltip("Play"));
        playPause_button.setGraphic(playPause_view);
        playPause_view.setImage(playIcon);
        /*
        playPause_button.setOnAction(e->{
            if (songCompleted) {
                musicPlayer.restart();
                songCompleted = false;
                playPause_view.setImage(pauseIcon);
            } else {
                if (isPlaying) {
                    musicPlayer.pauseMedia();
                    playPause_view.setImage(playIcon);
                } else {
                    musicPlayer.playMedia();
                    playPause_view.setImage(pauseIcon);
                }
            }
        });*/
        
        favourite_button = new Button();
        favourite_button.setGraphic(favourite_view);
        
        favourite_button.setOnAction(e->{
            favourite_view.setImage(favouriteIconFill);
        });
        
        volume_button = new Button();
        volume_button.setGraphic(volume_view);
        
        totalTime = new Label("00:00");
        
        currentTime = new Label("00:00");
        
    }
    
    private void setProgressBar() {
        
        progress_bar = new Slider();
        progress_bar.getStyleClass().add("progressBar");
        progress_bar.setMax(100);
        progress_bar.setMin(0);
        progress_bar.setValue(0);
        
        double width = Math.round(APP_WIDTH * 0.6);
        progress_bar.setPrefWidth(width);
        /*
        progress_bar.setOnMouseClicked(e->{
            musicPlayer.playFrom(progress_bar.getValue());
            valueChangingBOM = false;
        });
        
        progress_bar.setOnMousePressed(e->{
            valueChangingBOM = true;
        });*/
        
    }
    
    public VBox getMainLayout() {
        return mainBar;
    }
    
    public List<HBox> getSubLayouts() {
        return subLayouts;
    }
    
    public ImageViewer getAlbumViewer() {
        return album_view;
    }
    
    public List<Image> getImageList() {
        return imageList;
    }
    
    public List<ImageView> getImageViewList() {
        return imageViewList;
    }
    
    public List<Button> getButtonList() {
        return buttonList;
    }
    
    public List<Label> getLabelList() {
        return labelList;
    }
    
    public Slider getProgressBar() {
        return progress_bar;
    }
    
    public double[] getStatusBarSize() {
        return new double[]{statusBarWidth, statusBarHeight};
    }
    
}
