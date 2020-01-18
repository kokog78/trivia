package com.adaptionsoft.games.uglytrivia;

public class Game {
	
	private final Players players = new Players();
	private final Places places = new Places();
    
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
		places.resetPlace(howManyPlayers());
		players.resetPurse(howManyPlayers());
	    players.setPenaltyFor(howManyPlayers(), false);
	    
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
		
		if (players.isPenalty(currentPlayer)) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				println(players.getPlayer(currentPlayer) + " is getting out of the penalty box");
				changeCurrentPlayerPlace(roll);
			} else {
				println(players.getPlayer(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}
			
		} else {
		
			changeCurrentPlayerPlace(roll);
	
		}
		
	}
	
	private void changeCurrentPlayerPlace(int roll) {
		
		places.changePlace(currentPlayer, roll);
		
		println(players.getPlayer(currentPlayer) 
				+ "'s new location is " 
				+ places.getPlaceOf(currentPlayer));
		println("The category is " + currentCategory());
		askQuestion();
		
	}
	
	private void askQuestion() {
		println(questions.askQuestion(currentCategory()));
	}
	
	private String currentCategory() {
		return places.getCategoryOf(currentPlayer);
	}

	public boolean wasCorrectlyAnswered() {
		
		boolean winner;
		
		if (players.isPenalty(currentPlayer)){
			if (isGettingOutOfPenaltyBox) {
				
				onCorrectAnswer();
				winner = didPlayerWin();
				
			} else {
				winner= true;
			}
			
		} else {
		
			onCorrectAnswer();
			winner = didPlayerWin();
			
		}
		nextPlayer();
		return winner;
	}
	
	private void onCorrectAnswer() {
		
		boolean notPenality=!players.isPenalty(currentPlayer);
		
		//FIXME TYPO
		if(notPenality) {
			
			println("Answer was corrent!!!!");
		
		}else {
			
			println("Answer was correct!!!!");
			
		}
		players.incPurse(currentPlayer);
		println(players.getPlayer(currentPlayer) 
				+ " now has "
				+ players.getPurse(currentPlayer)
				+ " Gold Coins.");
		
		
	}
	
	private void nextPlayer() {
		
		currentPlayer++;
		if (currentPlayer == players.getNumberOfPlayers()) currentPlayer = 0;
		
	}
	
	public boolean wrongAnswer(){
		println("Question was incorrectly answered");
		println(players.getPlayer(currentPlayer)+ " was sent to the penalty box");
		players.setPenaltyFor(currentPlayer, true);
		
		nextPlayer();
		return true;
	}


	// FIXME wrong method name
	private boolean didPlayerWin() {
		return players.didPlayerWin(currentPlayer);
	}
}
