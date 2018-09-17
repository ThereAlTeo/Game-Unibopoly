package view.gameDialog;

import controller.ControllerImpl;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilities.IconLoader;
import utilities.enumerations.ClassicType;

public class SettingsDialog extends Dialog {

	private static final SettingsDialog SINGLETON = new SettingsDialog ();
	
	private Button saveGameBtn;
	
	private SettingsDialog() {
		
	}
	
	/**
	 * Instance of SettingsDialog.
	 * 
	 * @return the instance.
	 */
	public static SettingsDialog getSettingsDialog() {
		return SINGLETON;
	}
	
	/**
	 * Creation of the pane for the dialog.
	 */
	public void createSettingDialog() {
		final Stage stage = setStage();
		stage.initModality(Modality.WINDOW_MODAL);
		final ControllerImpl controller = ControllerImpl.getController();
		
		final FlowPane root = new FlowPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(getPrefWSize()/12);
		
		final Button musicButton = new Button();
		musicButton.setGraphic(controller.getSound().isMusicMute() ? IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getNoMusicImage()) : IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getMusicImage()));
		musicButton.getStyleClass().add("roundButton");
		root.getChildren().add(musicButton);
		
		final Button soundButton = new Button();
		soundButton.setGraphic(controller.getSound().isSoundMute() ? IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getNoSoundImage()) : IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getSoundImage()));
		soundButton.getStyleClass().add("roundButton");
		root.getChildren().add(soundButton);
		
		this.saveGameBtn = new Button();
		this.saveGameBtn.setGraphic(IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getSaveImage()));
		this.saveGameBtn.getStyleClass().add("roundButton");
		this.saveGameBtn.setTooltip(new Tooltip("Save the game!"));
		root.getChildren().add(this.saveGameBtn);
		
		musicButton.setOnAction(e -> {
			controller.getSound().changeMusicMute();
			musicButton.setGraphic(controller.getSound().isMusicMute() ? IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getNoMusicImage()) : IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getMusicImage()));
		});
		
		soundButton.setOnAction(e -> {
			controller.getSound().changeSoundsMute();
			soundButton.setGraphic(controller.getSound().isSoundMute() ? IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getNoSoundImage()) : IconLoader.getLoader().getImageFromPath(ClassicType.Other.GENERALOTHERIMAGEMAP.getSoundImage()));
		});
		
		this.saveGameBtn.setOnAction(e -> {
			controller.saveGame();
			stage.close();
		});
		
		stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if(!isNowFocused) {
				stage.close();
			}
		});
		
		final Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
		stage.setWidth(getPrefWSize()/2);
		stage.setHeight(getPrefWSize()/6);
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	public void changeSaveButton() {
		this.saveGameBtn.setDisable(!this.saveGameBtn.isDisable());
	}

}
