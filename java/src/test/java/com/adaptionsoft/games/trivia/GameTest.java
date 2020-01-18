package com.adaptionsoft.games.trivia;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameTest {
	
	private Game aGame = new Game();
	private StringBuffer out = new StringBuffer();
	
	@Before
	public void init() {
		
		aGame.setActConsole(obj -> {
			
			out.append(obj);
			out.append("\n");
			
		});
		
	}

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
	
	@Test
	public void wrong_answer_in_pop_category_goes_to_penalty() throws Exception {
		aGame.add("Bob");
		aGame.roll(4);
		aGame.wrongAnswer();
		assertConsole("Pop", "incorrectly", "penalty");
	}
	
	@Test
	public void kids_wrong_answer_in_pop_category_goes_to_penalty() throws Exception {
		aGame.addKid("Bob");
		aGame.roll(4);
		aGame.wrongAnswer();
		assertConsole("Pop", "incorrectly", "penalty");
	}
	
	@Test
	public void wrong_answer_in_science_category_goes_to_penalty() throws Exception {
		aGame.add("Bob");
		aGame.roll(1);
		aGame.wrongAnswer();
		assertConsole("Science", "incorrectly", "penalty");
	}
	
	@Test
	public void kids_wrong_answer_in_science_category_does_not_go_to_penalty() throws Exception {
		aGame.addKid("Bob");
		aGame.roll(1);
		aGame.wrongAnswer();
		assertConsole("Science", "incorrectly");
		assertNotConsole("penalty");
	}
	
	@Test
	public void player_wins_after_6_correct_answers() throws Exception {
		aGame.add("Bob");
		aGame.roll(1);
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		boolean result = aGame.wasCorrectlyAnswered();
		assertThat(result).isFalse();
	}
	
	@Test
	public void player_does_not_win_after_4_correct_answers() throws Exception {
		aGame.add("Bob");
		aGame.roll(1);
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		boolean result = aGame.wasCorrectlyAnswered();
		assertThat(result).isTrue();
	}
	
	@Test
	public void kid_wins_after_4_correct_answers() throws Exception {
		aGame.addKid("Bob");
		aGame.roll(1);
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		aGame.wasCorrectlyAnswered();
		boolean result = aGame.wasCorrectlyAnswered();
		assertThat(result).isFalse();
	}
	
	@Test
	public void prints_Player_Name() {
		
		aGame.add("Bob");
		assertConsole("Bob");
		
	}
	
	@Test
	public void prints_Kid_Player_Name() {
		
		aGame.addKid("Bob");
		assertConsole("Bob");
		assertNotConsole("Bob*");
		
	}
	
	private void assertConsole(String ... substrings) {
		assertThat(out.toString()).contains(substrings);
	}
	
	private void assertNotConsole(String ... substrings) {
		assertThat(out.toString()).doesNotContain(substrings);
	}
}
