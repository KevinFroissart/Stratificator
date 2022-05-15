package com.mif14;

import com.mif14.model.Program;
import com.mif14.model.Stratification;
import com.mif14.model.Stratifier;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        if (args.length % 2 == 0) showHelp();
        String inputFilename = args[args.length - 1];
        PrintStream output = parseArgs(args);
        Program program = Parser.parse(inputFilename);
        System.out.println("Stratified program:");
        program.printOutput();
        Stratification stratification = Stratifier.stratificate(program);
        stratification.writeInFile(output);
        output.close();
    }

    /**
     * Parses the given arguments and return the output PrintStream.
     *
     * @param args The arguments.
     * @return A {@link PrintStream}.
     */
    private static PrintStream parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-o" -> {
                    i++;
                    if ((i >= args.length)) showHelp();
                    try {
                        return new PrintStream(args[i]);
                    } catch (FileNotFoundException e) {
                        showHelp();
                    }
                }
                case "-h" -> showHelp();
            }
        }
        return System.out;
    }

    /**
     * Prints the help.
     */
    private static void showHelp() {
        String stringBuilder = """
                Usage : stratificator [OPTION] SOURCE
                -o [DEST]\tspecify the output file
                -h\t\t\tshow this help
                """;
        System.out.println(stringBuilder);
        System.exit(0);
    }
}
