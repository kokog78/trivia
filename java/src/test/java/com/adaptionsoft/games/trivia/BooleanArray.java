package com.adaptionsoft.games.trivia;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BooleanArray {
	
	public final Boolean[] bools;

	public BooleanArray(Boolean... bools) {
		super();
		this.bools = bools;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(bools);
	}

}
