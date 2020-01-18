package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
	
	private List<String> players = new ArrayList<>();
    private int[] places = new int[6];
    private int[] purses  = new int[6];
    private boolean[] inPenaltyBox  = new boolean[6];
    
    private LinkedList<String> popQuestions = new LinkedList<>();
    private LinkedList<String> scienceQuestions = new LinkedList<>();
    private LinkedList<String> sportsQuestions = new LinkedList<>();
    private LinkedList<String> rockQuestions = new LinkedList<>();
    
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;
    private Console actConsole;
    
    
    public  Game() {
    	for (int i = 0; i < 50; i++) {
			popQuestions.add(createPopQuestion(i));
			scienceQuestions.add(createScienceQuestion(i));
			sportsQuestions.add(createSportQuestion(i));
			rockQuestions.add(createRockQuestion(i));
    	}
    	
    	actConsole=new Console() {
			
			@Override
			public void println(Object output) {
				
				System.out.println(output);
				
			}
		};
    }
    
    public void setActConsole(Console actConsole) {
		this.actConsole = actConsole;
	}
    
    protected void println(Object output) {
    	
    	actConsole.println(output);
    	
    }

	private String createRockQuestion(int index) {
		return createQuestion("Rock", index);
	}
	
	private String createSportQuestion(int index) {
		return createQuestion("Sports", index);
	}
	
	private String createScienceQuestion(int index) {
		return createQuestion("Science", index);
	}
	
	private String createPopQuestion(int index) {
		return createQuestion("Pop", index);
	}
	
	private String createQuestion(String type, int index) {
		return String.format("%s Question %d", type, index);
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    actConsole.addPlayer(playerName);
	    actConsole.numberOfPlayers(players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		actConsole.numberOfPlayers(players.get(currentPlayer));
		actConsole.roll(roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				println("The category is " + currentCategory());
				askQuestion();
			} else {
				println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			println(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				println("Answer was correct!!!!");
				purses[currentPlayer]++;
				println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		println("Question was incorrectly answered");
		println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
