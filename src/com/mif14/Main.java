package com.mif14;

import com.mif14.model.Program;

public class Main {

    public static void main(String[] args) {
        String file = "src/com/mif14/resources/exemple1.dl";
        Program program = Parser.parse(file);
        program.printOutput();
    }
}
