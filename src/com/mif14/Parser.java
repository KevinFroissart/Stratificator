package com.mif14;

import com.mif14.model.Expression;
import com.mif14.model.Program;
import com.mif14.model.Rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public static String NOT_PATTERN = "\\s*(not)?\\s*";
    public static String EXPRESSION_PATTERN = "\\s*\\w+\\s*\\(\\s*'?\\w+'?\\s*(,\\s*'?\\w+'?\\s*)*\\)\\s*";

    /**
     * Reads and parses a given file into a list of EDBs and rules.
     *
     * @param filename The file to parse.
     * @return A {@link Program}.
     */
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
            System.err.println("File " + filename + " not found");
            System.exit(1);
        }
        return program;
    }

    /**
     * Prints an error message.
     *
     * @param lineNumber The line where the error occurred.
     */
    private static void printErrorMessage(int lineNumber) {
        System.err.println("Error Line " + lineNumber);
    }

    /**
     * Removes comments from a given line.
     *
     * @param line The line to handle.
     * @return The line without comments.
     */
    private static String handleComment(String line) {
        line = line.trim();
        if (line.contains("%") && line.charAt(0) != '%') {
            return line.substring(0, line.indexOf('%')).trim();
        }
        return line.contains("%") ? "" : line;
    }

    /**
     * Checks id a given line matches the EDB regex.
     *
     * @param line The line to check.
     * @return True if is an EDB, false otherwise.
     */
    private static boolean isEDB(String line) {
        return line.matches(EXPRESSION_PATTERN + "\\.");
    }

    /**
     * Checks id a given line matches the IDB regex.
     *
     * @param line The line to check.
     * @return True if is an IDB, false otherwise.
     */
    private static boolean isIDB(String line) {
        String body = NOT_PATTERN + EXPRESSION_PATTERN + "(," + NOT_PATTERN + EXPRESSION_PATTERN + ")*\\s*\\.";
        return line.matches(EXPRESSION_PATTERN + "\\s*:-\\s*" + body);
    }
}
