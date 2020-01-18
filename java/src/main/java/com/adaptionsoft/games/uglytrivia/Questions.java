package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {

    private LinkedList<String> popQuestions = new LinkedList<>();
    private LinkedList<String> scienceQuestions = new LinkedList<>();
    private LinkedList<String> sportsQuestions = new LinkedList<>();
    private LinkedList<String> rockQuestions = new LinkedList<>();

    public void createQuestion() {
    	for (int i = 0; i < 50; i++) {
    		addPopQuestion(i);
			addScienceQuestion(i);
			addSportQuestion(i);
			addRockQuestion(i);
    	}
    }
    
	public String askQuestion(String category) {
		// FIXME String euqality!!!
		if (category == "Pop")
			return popQuestions.removeFirst();
		if (category == "Science")
			return scienceQuestions.removeFirst();
		if (category == "Sports")
			return sportsQuestions.removeFirst();
		if (category == "Rock")
			return rockQuestions.removeFirst();
		return "";
	}

	private void addPopQuestion(int i) {
    	popQuestions.add(createPopQuestion(i));
    }
    
    private void addScienceQuestion(int i) {
    	scienceQuestions.add(createScienceQuestion(i));
    }
    
    private void addSportQuestion(int i) {
    	sportsQuestions.add(createSportQuestion(i));
    }
    
    private void addRockQuestion(int i) {
    	rockQuestions.add(createRockQuestion(i));
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
}
