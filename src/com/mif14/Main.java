package com.mif14;

import com.mif14.model.Program;
import com.mif14.model.Stratification;
import com.mif14.model.Stratifier;

import java.io.FileNotFoundException;
import java.io.PrintStream;


public class Main {

    private static boolean verbosity = false;

    public static void main(String[] args) {
        if (args.length < 1) {
            showHelp();
        }
        PrintStream output = parseArgs(args);
        String inputFilename = args[args.length - 1];
        Program program = Parser.parse(inputFilename);
        if (verbosity) printProgram(program);
        Stratification stratification = Stratifier.stratificate(program);
        stratification.writeInFile(output);
        output.close();
    }

    /**
     * Prints the stratified {@link Program}.
     *
     * @param program The {@link Program} to print.
     */
    private static void printProgram(Program program) {
        System.out.println("Stratified program:");
        program.printOutput();
    }

    /**
     * Parses the given arguments and return the output PrintStream.
     *
     * @param args The arguments.
     * @return A {@link PrintStream}.
     */
    private static PrintStream parseArgs(String[] args) {
        PrintStream outputStream = System.out;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-o" -> {
                    i++;
                    if (i >= args.length || args[i].startsWith("-")) showHelp("You must specify a filename");
                    try {
                        outputStream = new PrintStream(args[i]);
                    } catch (FileNotFoundException e) {
                        showHelp("File could not be created");
                    }
                }
                case "-v" -> {
                    verbosity = true;
                    if ((args.length - i) % 2 == 1) showHelp("You must specify a input file");
                }
                case "-h" -> showHelp();
            }
        }
        return outputStream;
    }

    /**
     * Prints the help and an error message before if provided.
     */
    private static void showHelp(String message) {
        System.err.println(message);
        showHelp();
    }

    /**
     * Prints the help.
     */
    private static void showHelp() {
        String stringBuilder = """
                Usage : stratificator [OPTION] SOURCE
                -o [DEST]\tspecify the output file
                -v\t\t\tadd verbosity and print parsed program
                -h\t\t\tdisplay this help and exit
                """;
        System.out.println(stringBuilder);
        System.exit(0);
    }
}
