package com.mif14;

import com.mif14.model.Program;

public class Main {

	public static void main(String[] args) {
		Program program = new Program();
		String file = "src/com/mif14/resources/exemple1.dl";
		Parser.parse(file, program);
		program.printOutput();
	}
}
