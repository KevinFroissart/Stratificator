package com.mif14;

import com.mif14.model.Program;
import com.mif14.model.Stratification;
import com.mif14.model.Stratifier;

public class Main {

    public static void main(String[] args) {
        String file = "src/com/mif14/resources/real_program2.dl";
        Program program = Parser.parse(file);
        program.printOutput();
        Stratification stratification = Stratifier.stratificate(program);
        System.out.println(stratification);
    }
}
