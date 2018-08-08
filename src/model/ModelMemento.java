package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import model.player.Player;
import model.tiles.Obtainable;

/**
 * Captures and externalize a partial snapshot of a Model's subclass internal
 * state, so that the subclass can be serialized or loaded from a file.
 */
public class ModelMemento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2066419006358603603L;

	private final Board gameBoard;
	private final List<Player> players;
	private final int currentPlayer;
	private final Set<Obtainable> properties;
	// private final Set<Imprevisti> imprevistis;
	// private final Set<Probabilitā> probabilitās;

	/**
	 * It creates an instance of ModelMemento.
	 * 
	 * @param gameBoard
	 *            the game board.
	 * @param players
	 *            the players of the game.
	 * @param currentPlayer
	 *            the current player.
	 * @param properties
	 *            the properties of the game.
	 * -- imprevisti e probabilitā --
	 */
	public ModelMemento(final Board gameBoard, final List<Player> players, final int currentPlayer,
			final Set<Obtainable> properties /*
												 * final Set<Imprevisti> imprevistis, final Set<Probabilitā>
												 * probabilitās
												 */) {
		this.gameBoard = gameBoard;
		this.players = players;
		this.currentPlayer = currentPlayer;
		this.properties = properties;
		// imprevisti e probabilitā
	}

	/**
	 * @return the game board.
	 */
	public Board getGameBoard() {
		return this.gameBoard;
	}

	/**
	 * @return a list of all players in game.
	 */
	public List<Player> getPlayers() {
		return this.players;
	}

	/**
	 * @return the current player.
	 */
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * @return the all the properties.
	 */
	public Set<Obtainable> getProperties() {
		return this.properties;
	}
}
