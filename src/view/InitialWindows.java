package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 *  
 * @author Matteo Alesiani 
 */

public class InitialWindows extends Application {

	private final Stage initialWindow = new Stage();
	
	/**
     * Constructor of the class. It sets up the Stage.
     */
	public InitialWindows() {
	}
	
	/**
     * It starts the JavaFX application.
     */
    @Override
    public void start(final Stage primaryStage) {
        //this.primaryWindow.getIcons().add(ImageLoader.getLoader().getImageFromPath("Icons/icon.png"));
        
        /*
         * TODO: 
         * this.mainWindow.setOnCloseRequest(e -> {
            e.consume();
            if (ViewImpl.getController().isGameLoopRunning()) {
                ViewImpl.getController().pauseGameLoop();
            }
            ClosureHandler.closeProgram(this.mainWindow);
        });*/

        this.initialWindow.setScene(CommandBridge.get(this.initialWindow));
        this.initialWindow.setResizable(false);
        this.initialWindow.show();
    }
}