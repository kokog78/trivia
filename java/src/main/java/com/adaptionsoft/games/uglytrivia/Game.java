package com.adaptionsoft.games.uglytrivia;

public class Game {
	
	private final static int NUMBER_OF_PLACES = 12;
	
	private final Players players = new Players();
    private int[] places = new int[6];
    private int[] purses  = new int[6];
    private boolean[] inPenaltyBox  = new boolean[6];
    
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;
    private Console actConsole;
	Questions questions = new Questions();
    
    
    public Game() {
    	questions.createQuestion();
    	actConsole = new Console() {
			
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
	
	public boolean isPlayable() {
		return players.isPlayable();
	}

	public boolean add(String playerName) {
		// FIXME first set place & purse to 0
		players.addPlayer(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    actConsole.addPlayer(playerName);
	    actConsole.numberOfPlayers(players.getNumberOfPlayers());
		return true;
	}
	
	
	
	public int howManyPlayers() {
		return players.getNumberOfPlayers();
	}

	public void roll(int roll) {
		actConsole.numberOfPlayers(players.getPlayer(currentPlayer));
		actConsole.roll(roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				println(players.getPlayer(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] >= NUMBER_OF_PLACES) {
					places[currentPlayer] = places[currentPlayer] - NUMBER_OF_PLACES;
				}
				
				println(players.getPlayer(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				println("The category is " + currentCategory());
				askQuestion();
			} else {
				println(players.getPlayer(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] >= NUMBER_OF_PLACES) {
				places[currentPlayer] = places[currentPlayer] - NUMBER_OF_PLACES;
			}
			
			println(players.getPlayer(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		println(questions.askQuestion(currentCategory()));
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
				println(players.getPlayer(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			println(players.getPlayer(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		println("Question was incorrectly answered");
		println(players.getPlayer(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
