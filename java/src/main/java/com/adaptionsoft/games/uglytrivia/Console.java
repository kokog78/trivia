package com.adaptionsoft.games.uglytrivia;

public interface Console {
	
    public void println(Object output);
    
    public default void addPlayer(String playerName) {
    	
    	println(playerName + " was added");
    	
    }
    
    public default void numberOfPlayers(int playerNumb ) {
    	
    	println("They are player number " + playerNumb);
    	
    }
    
    public default void numberOfPlayers(Object player) {
    	
    	println(player + " is the current player");
    	
    }
    
    public default void roll(int roll) {
    	
    	println("They have rolled a " + roll);
    	
    }

}
