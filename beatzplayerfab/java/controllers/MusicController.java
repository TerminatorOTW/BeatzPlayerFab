
// Developed by the TerminatorOTW

package beatzplayerfab.java.controllers;

import beatzplayerfab.java.player.MusicPlayer;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.util.Duration;

public class MusicController {
    
    private final MusicPlayer player;
    private final MainController mainCtrl;
    
    private boolean isPlaying = false;
    
    private boolean isFinished = false;
    
    private final List<String> musicFilesName = new ArrayList<>();
    private final List<URL> musicFilesPaths = new ArrayList<>();
    
    private int currentMusicIndex = 0;
    
    public MusicController(MusicPlayer mP, MainController mC) {
        this.player = mP;
        this.mainCtrl = mC;
    }
    
    public void playPause() {
        if (player.getMediaPlayer() != null) {
            if (isPlaying && !isFinished) {
                player.pauseMedia();
            } else if (isFinished) {
                player.restart();
            } else {
                player.playMedia();
            }
        }
    }
    
    public void setIsPlaying(boolean v) {
        isPlaying = v;
    }
    
    public void setIsFinished(boolean v) {
        isFinished = v;
    }
    
    public boolean isPlaying() {
        return isPlaying;
    }
    
    public boolean isFinished() {
        return isFinished;
    }
    
    public boolean canPlayNext() {
        return (currentMusicIndex + 1) < musicFilesPaths.size();
    }
    
    public boolean canPlayPrev() {
        return (currentMusicIndex - 1) >= 0;
    }
    
    public void addMusic(URL url, String fileName) {
        
        if (!musicFilesPaths.contains(url)) {
            musicFilesPaths.add(url);
            musicFilesName.add(fileName);
        }
        
        if (!isPlaying()) {
            if (canPlay()) {
                player.playAudio(musicFilesPaths.get(currentMusicIndex));
                setRunnablesOnMedia();
            }
        }
    }
    
    public void addMusic(File[] file) {
        
        if (file != null) {
            for (int x = 0; x < file.length; x++) {
                URL currentAudioUrl = null;
                try {
                    currentAudioUrl = file[x].toURI().toURL();
                } catch (MalformedURLException ex) {}
                
                if (currentAudioUrl != null) {
                    musicFilesPaths.add(currentAudioUrl);
                    musicFilesName.add(file[x].getName());
                }
            }
                 
            if (!isPlaying()) {
                if (canPlay()) {
                    player.playAudio(musicFilesPaths.get(currentMusicIndex));
                    setRunnablesOnMedia();
                }
            }
        }
        
    }
    
    public boolean canPlay() {
        if (currentMusicIndex < musicFilesPaths.size() && currentMusicIndex >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean playNext() {
        if (canPlayNext()) {
            currentMusicIndex += 1;
            player.playAudio(musicFilesPaths.get(currentMusicIndex));
            setRunnablesOnMedia();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean playPrev() {
        if (canPlayPrev()) {
            currentMusicIndex -= 1;
            player.playAudio(musicFilesPaths.get(currentMusicIndex));
            setRunnablesOnMedia();
            return true;
        } else {
            return false;
        }
    }
    
    public Media getCurrentMedia() {
        if (player.getMediaPlayer() != null)
            return player.getCurrentMedia();
        return null;
    }
    
    public Duration getCurrentDuration() {
        if (player.getMediaPlayer() != null)
            return player.getMediaPlayer().getCurrentTime();
        return null;
    }
    
    public Duration getTotalDuration() {
        if (player.getMediaPlayer() != null)
            return player.getMediaPlayer().getTotalDuration();
        return null;
    }
    
    public void setOnReady(Runnable r) {
        if (player.getMediaPlayer() != null)
            player.getMediaPlayer().setOnReady(r);
    }
    
    public void setOnPlaying(Runnable r) {
        if (player.getMediaPlayer() != null)
            player.getMediaPlayer().setOnPlaying(r);
    }
    
    public void setOnPaused(Runnable r) {
        if (player.getMediaPlayer() != null)
            player.getMediaPlayer().setOnPaused(r);
    }
    
    public void setOnFinished(Runnable r) {
        if (player.getMediaPlayer() != null)
            player.getMediaPlayer().setOnEndOfMedia(r);
    }
    
    public ReadOnlyObjectProperty<Duration> currentTimeProperty() {
        if (player.getMediaPlayer() != null)
            return player.getMediaPlayer().currentTimeProperty();
        return null;
    }
    
    public String getTotalTime() {
        if (player.getMediaPlayer() != null)
            return player.getTotalTimeToString();
        return "";
    }
    
    public String getCurrentTime() {
        if (player.getMediaPlayer() != null)
            return player.getCurrentDurationToString();
        return "";
    }
    
    public void skipMusic(int value) {
        if (player.getMediaPlayer() != null)
            player.skip(value);
    }
    
    public double getVolume() {
        if (player.getMediaPlayer() != null)
            return player.getMediaPlayer().getVolume();
        return 0;
    }
    
    public void increaseVolumeBy(double value) {
        double x = getVolume() + value;
        setCurrentVolume(x);
    }
    
    public void decreaseVolumeBy(double value) {
        double x = getVolume() - value;
        setCurrentVolume(x);
    }
    
    public void setCurrentVolume(double v) {
        if (player.getMediaPlayer() != null)
            player.getMediaPlayer().setVolume(v);
    }
    
    public String getCurrentMediaName() {
        return musicFilesName.get(currentMusicIndex);
    }
    
    private void setRunnablesOnMedia() {
        
        player.getMediaPlayer().setOnReady(()->{
            Image album_image = 
                (Image) this.getCurrentMedia().getMetadata().get("image");

            if (album_image != null) {
                mainCtrl.getStatusBarController().changeAlbumImage(album_image);
            } else {
                Image image = mainCtrl.getStatusBarController().getDefaultAlbumImage();
                mainCtrl.getStatusBarController().changeAlbumImage(image);
            }

            String songArtist = 
                (String) this.getCurrentMedia().getMetadata().get("artist");

            if (songArtist == null)
                songArtist = "Unknown Artist";

            mainCtrl.getStatusBarController().setSongName(
                    "Song : " + this.getCurrentMediaName().replace(".mp3", "") + "\n" +
                            "Artist : " + songArtist
            );

            mainCtrl.getStatusBarController().setTotalMusicTime(
                this.getTotalTime()
            );
        });

        player.getMediaPlayer().setOnPlaying(()->{
            mainCtrl.getStatusBarController().setIconAsPlaying(true);
            this.setIsPlaying(true);
            this.setIsFinished(false);
        });

        player.getMediaPlayer().setOnPaused(()->{
            mainCtrl.getStatusBarController().setIconAsPlaying(false);
            this.setIsPlaying(false);
        });

        this.setOnFinished(()->{
            if (!this.playNext()) {
                mainCtrl.getStatusBarController().setIconAsPlaying(false);
                this.setIsPlaying(false);
                this.setIsFinished(true);
            } else {
                setRunnablesOnMedia();
            }
        });

        this.currentTimeProperty().addListener(g->{
            double percent =
                    Math.round(
                            (this.getCurrentDuration().toSeconds() * 100)
                                /
                            this.getTotalDuration().toSeconds()
                    );
            //if (!valueChangingBOM)
                mainCtrl.getStatusBarController().setProgressValue(percent);

            mainCtrl.getStatusBarController().setCurrentMusicTime(
                this.getCurrentTime()
            );
        });
        
    }
    
}
