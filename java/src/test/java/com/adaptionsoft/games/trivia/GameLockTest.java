package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

import org.approvaltests.legacycode.IndexPermutations;
import org.approvaltests.legacycode.LegacyApprovals;
import org.approvaltests.legacycode.Range;
import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameLockTest {
	
	private String[] players = new String[] {"Chet", "Pat"};
	
	private BooleanArray[] answers = new BooleanArray[] {
		new BooleanArray (true, true, true),
		new BooleanArray (true, false, true),
		new BooleanArray (false, true, true),
		new BooleanArray (false, false, true),
		new BooleanArray (true, true, false),
		new BooleanArray (true, false, false),
		new BooleanArray (false, true, false),
		new BooleanArray (false, false, false)
	};
	
	@Test
	public void lockDownGame() {
		LegacyApprovals.LockDown(this, "game",
				Range.get(1, 6),
				answers);
	}

	public Object game(Integer roll, BooleanArray correctAnswers) {
		StringBuffer output = new StringBuffer();
		Game game = new Game();
		game.setActConsole(obj -> {
			output.append(obj);
			output.append('|');
		});
		
		for (String player : players) {
			game.add(player);
		}
		
		int counter=0;
		boolean inProg=true;
		
		boolean falseAns=false;
		do {
			
			for (Boolean correct : correctAnswers.bools) {
				
				if(falseAns) { 
					
					correct=true;
				
				}
				
				counter++;
				game.roll(roll);
				if (correct) {
					inProg=game.wasCorrectlyAnswered();
				} else {
					inProg=game.wrongAnswer();
				}
				
				if(!inProg) {
					
					break;
					
				}
			}
		
			falseAns = true;
			roll=3;
			
		}while(inProg);
		
		output.append('|');
		output.append(counter);
		
		return output.toString();
	}
	
}
