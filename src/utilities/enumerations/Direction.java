package utilities.enumerations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 * Enumeration for the 4 possible directions of the entities.<br>
 * List of Possible Direction:<br>
 * <b>North (N)</b><br>
 * <b>East (E)</b><br>
 * <b>South (S)</b><br>
 * <b>West (W)</b><br>
 */
public enum Direction {
    
	/**
     * West.
     */
	W(0) {
		@Override
		public void moveLocation(Scene scene, Node player) {
			transition.setFromX(player.getTranslateX());
            transition.setFromY(player.getTranslateY());
            transition.setToX(player.getTranslateX() - STEP_SIZE);
            transition.setToY(player.getTranslateY());
            transition.playFromStart();			
		}
	},
	/**
     * North.
     */
	N(1) {
		@Override
		public void moveLocation(Scene scene, Node player) {
			transition.setFromX(player.getTranslateX());
            transition.setFromY(player.getTranslateY());
            transition.setToX(player.getTranslateX());
            transition.setToY(player.getTranslateY() - STEP_SIZE);
            transition.playFromStart();
		}
	},
    /**
     * East.
     */
	E(2) {
		@Override
		public void moveLocation(Scene scene, Node player) {
			transition.setNode(player);
			transition.setFromX(player.getTranslateX());
            transition.setFromY(player.getTranslateY());
            transition.setToX(player.getTranslateX() + STEP_SIZE);
            transition.setToY(player.getTranslateY());
            transition.playFromStart();

		}
	},
	 /**
     * South.
     */
	S(3) {
		@Override
		public void moveLocation(Scene scene, Node player) {
			transition.setNode(player);
			transition.setFromX(player.getTranslateX());
            transition.setFromY(player.getTranslateY());
            transition.setToX(player.getTranslateX());
            transition.setToY(player.getTranslateY() + STEP_SIZE);
            transition.playFromStart();

		}
	};
	
	private static final int STEP_SIZE = 100;
	private static final Duration DURATION = Duration.millis(500);
	
	private static final TranslateTransition transition = new TranslateTransition(DURATION);
    
    //internal index
    private int index; 
    
	/**
	 * Constructor
	 * @param initindex index of the Direction
	 */
	private Direction(final int initindex) {
		this.index = initindex;
	}

	/**
	 * Move the input location (loc) in the specified direction from which this method is called.<br>
	 * If the Direction is oblique, the movement velocity used is DIAGONALVELOCITY.
	 * @param loc Location to move
	 * @param d Velocity of the movement
	 */
	public abstract void moveLocation(Scene scene, Node player);

	/**
	 * Get the index of the direction
	 * @return index the index of the direction
	 */
	private int getIndex() {
		return this.index;
	}

	/**
	 * 
	 * @return 
	 */
	public Direction rotation() {
		return Direction.values()[(this.getIndex() + 1) % Direction.values().length];
	}
}

