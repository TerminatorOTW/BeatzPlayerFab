
// Developed by the TerminatorOTW

package beatzplayerfab.java.player;

import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {
    
    private Media media;
    private MediaPlayer player;
    
    public MusicPlayer() {
    }
    
    public MusicPlayer(String url) {
        setNewMedia(url);
    }
    
    public void playMedia() {
        if (player != null) {
            player.play();
        }
    }
    
    public void pauseMedia() {
        if (player != null)
            player.pause();
    }
    
    public void setVolume(int value) {
        if (player != null)
            player.setVolume(value);
    }
    
    public void restart() {
        player.seek(Duration.ZERO);
    }
    
    public void skip(int value) {
        player.seek(player.getCurrentTime().add(Duration.seconds(value)));
    }
    
    public void playFrom(double percent) {
        double time;
        if (player != null) {
            time = Math.round(player.getTotalDuration().toSeconds() * (percent/100));
            player.seek(Duration.seconds(time));
        }
    }
    
    public void playAudio(URL filePath) {
        setNewMedia(filePath.toExternalForm());
        player.play();
    }
    
    public Image getAlbumArt() {
        Image album_art = (Image) this.media.getMetadata().get("image");
        return album_art;
    }
    
    public String getCurrentDurationToString() {
        if (player != null)
            return convertDurationToIntString(player.getCurrentTime());
        return "00:00:00";
    }
    
    public String getTotalTimeToString() {
        if (player != null)
            return convertDurationToIntString(player.getTotalDuration());
        return "00:00:00";
    }
    
    public Media getCurrentMedia() {
        return media;
    }
    
    public MediaPlayer getMediaPlayer() {
        return player;
    }
    
    /**
     * Methods only for this class
     */
    private void setNewMedia(String url) {
        
        if (this.player != null)
            this.player.stop();
        
        this.media = null;
        this.player = null;
        
        this.media = new Media(url);
        this.player = new MediaPlayer(media);
        
    }
    
    private String convertDurationToIntString(Duration d) {
        
        int duration_sec = (int) Math.round(d.toMinutes() * 60);
        
        int hours = 0;
        int minutes = 0;
        
        String hrs, min, sec;
        
        while(3600 < duration_sec) {
            hours += duration_sec;
            duration_sec %= 3600;
            hours = (hours - duration_sec) / 3600;
        }
        
        while(60 < duration_sec) {
            minutes += duration_sec;
            duration_sec %= 60;
            minutes = (minutes - duration_sec) / 60;
        }
        
        if (hours <= 9)
            hrs = "0" + hours;
        else
            hrs = hours + "";
        
        if (minutes <= 9)
            min = "0" + minutes;
        else
            min = minutes + "";
        
        if (duration_sec <= 9)
            sec = "0" + duration_sec;
        else
            sec = duration_sec + "";
        
        String duration = "";
        
        if (hours == 0) {
            duration += min + ":" + sec;
        } else {
            duration += hrs + ":" + min + ":" + sec;
        }
        
        return duration;
    }
    
}
