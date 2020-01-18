package com.adaptionsoft.games.uglytrivia;

public class Places {

	private final static int NUMBER_OF_PLACES = 12;
	
    private int[] places = new int[6];
    
	public void resetPlace(int player) {
		places[player] = 0;
	}
	
	public void changePlace(int player, int roll) {
		places[player] = places[player] + roll;
		if (places[player] >= NUMBER_OF_PLACES) {
			places[player] = places[player] - NUMBER_OF_PLACES;
		}
	}
	
	public int getPlaceOf(int player) {
		return places[player];
	}
	
	public String getCategoryOf(int player) {
		if (places[player] == 0) return "Pop";
		if (places[player] == 4) return "Pop";
		if (places[player] == 8) return "Pop";
		if (places[player] == 1) return "Science";
		if (places[player] == 5) return "Science";
		if (places[player] == 9) return "Science";
		if (places[player] == 2) return "Sports";
		if (places[player] == 6) return "Sports";
		if (places[player] == 10) return "Sports";
		return "Rock";
	}


}
