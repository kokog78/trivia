package com.adaptionsoft.games.trivia;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameTest {
	
	private Game aGame = new Game();

	@Test
	public void characterization_1() throws Exception {
		aGame.add("Chet");
		aGame.roll(3);
		boolean result = aGame.wrongAnswer();
		assertThat(result).isTrue();
	}
	
	@Test
	public void characterization_2() throws Exception {
		aGame.add("Chet");
		aGame.roll(3);
		boolean result = aGame.wasCorrectlyAnswered();
		assertThat(result).isTrue();
	}
}
