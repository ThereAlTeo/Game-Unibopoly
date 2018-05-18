package model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.Parse;
import utilities.ReadFile;
import utilities.enumerations.ClassicType;

/**
 * This class manage the game board. 
 * 
 * @author Matteo Alesiani
 */

public class Board {
	
	private static final int MAXINDEXBOARD = 40;
	
	private Set<Tile> gameBoard;
	private String mode;
	
	public Board(final String mode) {
		this.gameBoard = new HashSet<>();
		this.mode = mode;
		this.initializationSetTile();
	}
	
	/**
	 * Return the Set of all Obtainable Tile.
	 * 
	 * @return <tt>Set<Obtainable></tt> of all Obtainable.
	 */
	public Set<Obtainable> getObtainable(){
		return this.gameBoard.stream()
				   .filter(t -> t instanceof Obtainable)
				   .map(obj -> (ObtainableImpl) obj)
				   .collect(Collectors.toSet());
	} 
	
	/**
	 * Return the Set of all NotBuildable Tile.
	 * 
	 * @return <tt>Set<NotBuildable></tt> of all NotBuildable.
	 */
	public Set<NotBuildable> getNotBuildable(){
		return this.gameBoard.stream()
				   .filter(t -> t instanceof NotBuildable)
				   .map(obj -> (NotBuildableImpl) obj)
				   .collect(Collectors.toSet());
	}
	
	/**
	 * Return the Tile of the specific position.
	 * 
	 * @param  index  index of the position.
     * @throws IllegalArgumentException if the specified index is over the limit.
	 * @return <tt>Tile</tt> of the specific position.
	 */
	public Tile getTileOf(final int index) {
		if(index >= MAXINDEXBOARD) {
			throw new IllegalArgumentException();
		}
		
		return this.gameBoard.stream()
				   .filter(t -> t.getPosition() == index)
				   .findFirst().get();
	}
	
	/**
	 * Return the Set of all Tile.
	 * 
	 * @return <tt>Set<Tile></tt> of all Tile.
	 */
	public Set<Tile> getTileBoard(){
		return this.gameBoard;
	}
	
	/**
	 * Return the String corresponding to the mode of game.
	 * 
	 * @return <tt>mode</tt> of game.
	 */
	public String getModeGame() {
		return this.mode;
	}
	
	private void initializationSetTile() {
		try {
			ReadFile.readFile(ClassicType.GeneralPurposeMap.getStaticValuesInitFile())
					.map(Parse.PARSING_INIT_TILE_BOARD::apply)
					.forEach(gameBoard::add);
			/**
			 *	TODO: Gestire la funzione in modo da renderla ancora pi� generale !!! 
			 */
			ReadFile.readFile(ClassicType.GeneralPurposeMap.getClassicModeInitFile())
					.forEach(record -> this.gameBoard.stream()
													 .filter(t -> t.getPosition() == Integer.parseInt(record.substring(0, 0)))
													 .findFirst().get()
													 .setNameOf(record.substring(2)));
		}catch (IOException e) {}
		catch (Exception e) {}
		
		//Collections.nCopies(4, new NotBuildable(positionTile, price, mortgage, colorTile, typeOf))
		
	}
}
