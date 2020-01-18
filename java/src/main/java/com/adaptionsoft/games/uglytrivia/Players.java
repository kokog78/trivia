package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

	public static final String KINDER_MARK = "*";
	public static final int KINDER_WIN_COIN_COUNT = 4;
	public static final int DEFAULT_WIN_COIN_COUNT = 6;
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
		
		String actName=players.get(index);
		
		if(actName.endsWith(KINDER_MARK)) {
			
			actName=actName.substring(0, actName.length()-1);
			
		}
		
		return actName;
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
		int limit = isKinder(player) ? KINDER_WIN_COIN_COUNT : DEFAULT_WIN_COIN_COUNT;
		return !(getPurse(player) == limit);
	}

	public boolean isKinder(int player) {
		return players.get(player).endsWith(KINDER_MARK);
	}
}
