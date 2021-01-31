
// Developed by the TerminatorOTW

package beatzplayerfab.java;

import beatzplayerfab.java.layouts.MainLayout;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

public class BeatzPlayerFab extends Application {

    public static Stage windows;

    @Override
    public void start(Stage primaryStage) {
        
        // Stage
        windows = primaryStage;
        windows.initStyle(StageStyle.TRANSPARENT);
        windows.setTitle("BeatzPlayer");
        
        final Rectangle2D screen = Screen.getPrimary().getBounds();
        
        final double app_width = screen.getWidth() * 60/100;
        final double app_height = screen.getHeight() * 80/100;
        
        final MainLayout mainLayout = new MainLayout(windows, app_width, app_height);
        
        Scene scene = new Scene(mainLayout.getLayout());
        scene.setFill(Color.TRANSPARENT);
        
        windows.setScene(scene);
        windows.centerOnScreen();
        windows.setFullScreenExitHint("");
        
        windows.show();
    }
    
    public Stage getStage() {
        return windows;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
