package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.player.Player;
import model.player.PlayerInfo;
import model.player.PlayerInfo.Prison;
import utilities.CircularListImpl;

/**
 * @author Matteo Alesiani
 *
 */
public class TurnImpl implements Turn {

	private static final long serialVersionUID = -754218580225904481L;
	private static final int TURN_IN_JAIL = 3;
	
	private CircularListImpl<Player> turnManagement;
	private Map<PlayerInfo, Integer> jailMap;
	private int rolls;
	
	public TurnImpl(List<Player> players) {
		this.turnManagement = new CircularListImpl<>(players);
		this.jailMap = new HashMap<>();
		this.nextPlayer();
		this.clear();
	}
	
	public Player getCurrentPlayer() {
		return this.turnManagement.getHead();
	}

	@Override
	public boolean isInJail() {
		return this.getCurrentPlayer().isInJail();
	}
	
	@Override
	public void turnInJail() {
		this.jailMap.merge(this.getCurrentPlayer(), 0, (oldV, newV) -> oldV++);		
	}
	
	@Override
	public boolean exitFromJail() {
		if(this.jailMap.get(this.getCurrentPlayer()) == TURN_IN_JAIL) {
			this.jailMap.remove(this.getCurrentPlayer());
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void nextPlayer() {
		this.clear();
		this.turnManagement.shift();
	}

	@Override
	public boolean remove(Player player) {
		return this.turnManagement.remove(player);
	}
	
	@Override
	public List<PlayerInfo> getPlayers(){
		return this.turnManagement.stream().collect(Collectors.toList());
	}
	
	private void clear() {
		this.rolls = -1;
	}
	
	@Override
	public boolean isThrows() {
		this.rolls++;
		return this.rolls < 3;
	}
}
