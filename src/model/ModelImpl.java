package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import controller.ControllerImpl;
import model.player.Player;
import model.player.PlayerInfo;
import model.tiles.Obtainable;
import model.tiles.Tile;
import utilities.Pair;

public class ModelImpl implements Model{
	
	private static final int JAIL = 30;

	private Board board;
	private List<PlayerInfo> loserList;
	private Turn turnPlayer;
	
	public ModelImpl(final Board board, final List<Player> players) {
		this.board = board;
		this.loserList = new ArrayList<>();
		this.turnPlayer = new TurnImpl(players);
	}
	
	@Override
	public List<PlayerInfo> getPlayers() {
		return this.turnPlayer.getPlayers();
	}

	@Override
	public void saveGame() {
		ResourceManager.getInstance().saveOnFile(CareMementoTaker.getMementoInstance().getMemento());
	}

	/**
	 * Ricordarsi che solo i giocatori non in JAIL possono muoversi.
	 * 
	 * */
	@Override
	public Pair<Integer> exitDice() {
		Pair<Integer> temp = Dice.getInstance().getDice();
		if(this.turnPlayer.isThrows()) {
			
			if(this.turnPlayer.isInJail()){
				this.turnPlayer.turnInJail();
				
				if(temp.areSame() || this.turnPlayer.exitFromJail()) {
					this.exitFromJail();
				}
			}
	
			return temp; 
		}else {
			this.goToJail();
			return temp;
			//si pu� far comunque ritornare il risultato,
			//poich� se il giocatoee finisce in carcere prima di muoversi non si muove,
			//ma in questo modo � comunque possibile mostrare il risultato
		}
	}	
	
	@Override
	public Set<Tile> getBoard() {
		return this.board.getTiles(tile -> true).stream().collect(Collectors.toSet());
	}

	@Override
	public PlayerInfo getCurrentPlayer() {
		return this.turnPlayer.getCurrentPlayer();
	}
	
	public Set<Obtainable> getProperties(){ 
		return this.board.getTiles(tile -> tile instanceof Obtainable).stream()
				   .map(tile -> (Obtainable) tile).collect(Collectors.toSet());
	}

	//riguarda se � corretto playerInfo/player
	@Override
	public void removePlayer(PlayerInfo player) {
		this.loserList.add((Player) this.turnPlayer.getPlayers().remove(this.turnPlayer.getPlayers().indexOf(player)));
	}

	public void endTurn() {
		this.turnPlayer.nextPlayer();
	}

	@Override
	public void movement(int value) {
		if(!this.getCurrentPlayer().isInJail()) {
			this.setNewPosition((this.turnPlayer.getCurrentPlayer().getPosition() + value) % this.board.getTilesNumber());
		}
	}
	
	private void setNewPosition(int value) {
		this.turnPlayer.getCurrentPlayer().setPosition(value);
	}
	
	public void goToJail() {
		this.setNewPosition(JAIL);
		((Player) this.getCurrentPlayer()).goToJail();
		//this.endTurn();
	}
	
	public void exitFromJail() {
		this.turnPlayer.getCurrentPlayer().exitFromJail();
	}

	/*
	public void payment(PlayerInfo player, int moneyAmount) {
		if(!this.getCurrentPlayer().canPay(moneyAmount)) {
			if(moneyAmount > this.getCurrentPlayer().totalAssets()) {
				this.removePlayer(player);
			}
			ControllerImpl.getController().startMortgage(moneyAmount, player);
		} else {
			//((Player) player).decMoney(moneyAmount);
		}
	}
	
	public void buyProperty(Obtainable property) {
		if(this.getCurrentPlayer().canPay(property.getPrice())) {
			this.payments(getCurrentPlayer(), property.getPrice());
			((Player) this.getCurrentPlayer()).addProperty(property);
		} else {
			ControllerImpl.getController().startAuciton(property);
		}
	} */
	

	/**
	 * OSSERVAZIONI:
	 * -  LA gestione del turno dei giocatori dove va ?? Si potrebbe sapere il curruntPlayer tramite quella classe. 
	 *    Ci� non significa eliminare l'attributo, bens� inizializzrlo continuamente tramite quella classe.
	 * - Aggiungere un metodo che passa il turno al giocatore successivo. FATTO, ma verificare
	 * - 
	 */
	/**
	 * se pu� essere utile alle conseguenze del movimento si pu� mettere "buyProperty" nel model, in modo da rendere anche pi�
	 * efficente la generazione di aste
	 */
}
