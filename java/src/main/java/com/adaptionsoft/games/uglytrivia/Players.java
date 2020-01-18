package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

	private List<String> players = new ArrayList<>();

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
}
