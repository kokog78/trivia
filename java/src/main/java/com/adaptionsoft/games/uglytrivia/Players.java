package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

	private List<String> players = new ArrayList<>();
    private boolean[] inPenaltyBox  = new boolean[6];
    private int[] purses  = new int[6];

	public void addPlayer(String playerName) {
		players.add(playerName);
	}
	
	public int getNumberOfPlayers() {
		return players.size();
	}
	
	public String getPlayer(int index) {
		return players.get(index);
	}
	
	public boolean isPlayable() {
		return (getNumberOfPlayers() >= 2);
	}

	public void setPenaltyFor(int player, boolean penalty) {
		inPenaltyBox[player] = penalty;
	}
	
	public boolean isPenalty(int player) {
		return inPenaltyBox[ player];
	}
	
	public void resetPurse(int player) {
		purses[player] = 0;
	}
	
	public void incPurse(int player) {
		purses[player]++;
	}
	
	public int getPurse(int player) {
		return purses[player];
	}
	
	public boolean didPlayerWin(int player) {
		return !(getPurse(player) == 6);
	}

	
}
