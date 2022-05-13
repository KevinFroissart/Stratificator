package com.mif14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.mif14.model.Expression;
import com.mif14.model.Program;
import com.mif14.model.Rule;

public class Parser {

	public static void parse(String filename) {
		Program program = new Program();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (isComment(line)) {
					continue;
				}
				if (isEDB(line)) {
					program.addEdb(new Expression(line));
				}
				else if (isIDB(line)) {
					program.addRule(new Rule(line));
				}
				System.out.println(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isComment(String line) {
		return line.startsWith("%");
	}

	private static boolean isEDB(String line) {
		return line.matches("\\w+\\('?\\w+'?(,\\s*'?\\w+'?)*\\)\\.");
	}

	private static boolean isIDB(String line) {
		return line.matches("\\w+\\('?\\w+'?(,\\s*'?\\w+'?)*\\)\\s*:-\\s*\\w+\\('?\\w+'?(,'?\\w+'?)*\\)(,\\s*\\w+\\('?\\w+'?(,'?\\w+'?)*\\))*\\.");
	}
}
