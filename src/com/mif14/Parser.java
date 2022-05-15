package com.mif14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.mif14.model.Expression;
import com.mif14.model.Program;
import com.mif14.model.Rule;

public class Parser {

    public static String NOT_PATTERN = "\\s*(not)?\\s*";
    public static String EXPRESSION_PATTERN = "\\s*\\w+\\s*\\(\\s*'?\\w+'?\\s*(,\\s*'?\\w+'?\\s*)*\\)\\s*";

    public static Program parse(String filename) {
        Program program = new Program();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = handleComment(line);
                if (line.isEmpty()) {
                    continue;
                }
                if (isEDB(line)) {
                    program.addEdb(new Expression(line));
                    continue;
                }
                if (isIDB(line)) {
                    program.addRule(new Rule(line));
                    continue;
                }
                printErrorMessage(lineNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return program;
    }

    private static void printErrorMessage(int lineNumber) {
        System.err.println("Error Line " + lineNumber);
    }

    private static String handleComment(String line) {
        line = line.trim();
        if (line.contains("%") && line.charAt(0) != '%') {
            return line.substring(0, line.indexOf('%')).trim();
        }
        return line.contains("%") ? "" : line;
    }

    private static boolean isEDB(String line) {
        return line.matches(EXPRESSION_PATTERN + "\\.");
    }

    private static boolean isIDB(String line) {
        String body = NOT_PATTERN + EXPRESSION_PATTERN + "(," + NOT_PATTERN + EXPRESSION_PATTERN + ")*\\s*\\.";
        return line.matches(EXPRESSION_PATTERN + "\\s*:-\\s*" + body);
    }
}
