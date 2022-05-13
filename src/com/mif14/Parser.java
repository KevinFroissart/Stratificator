package com.mif14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.mif14.model.Expression;
import com.mif14.model.Program;
import com.mif14.model.Rule;

public class Parser {

	public static void parse(String filename) {
		Program program = new Program();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = handleComment(line);
				if (isEmpty(line)) {
					continue;
				}
				if (isEDB(line)) {
					program.addEdb(new Expression(line));
				}
				else if (isIDB(line)) {
					program.addRule(new Rule(line));
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("EDB:");
		System.out.println(program.getEdb().toString());
		System.out.println("IDB");
//		Arrays.toString(program.getRules());
	}

	private static String handleComment(String line) {
		line = line.trim();
		if (line.contains("%") && line.charAt(0) != '%') {
			return line.substring(0, line.indexOf('%')).trim();
		}
		return line.contains("%") ? "" : line;
	}

	private static boolean isEmpty(String line) {
		return line.length() == 0;
	}

	private static boolean isEDB(String line) {
		return line.matches("\\w+\\('?\\w+'?(,\\s*'?\\w+'?)*\\)\\.");
	}

	private static boolean isIDB(String line) {
		return line.matches("\\w+\\('?\\w+'?(,\\s*'?\\w+'?)*\\)\\s*:-\\s*\\w+\\('?\\w+'?(,'?\\w+'?)*\\)(,\\s*\\w+\\('?\\w+'?(,'?\\w+'?)*\\))*\\.");
	}
}
