package com.mif14.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Stratification {

    List<List<Rule>> strata;

    /**
     * Constructor.
     * Initialises an empty Stratification.
     *
     * @param stratumNumber the number of strata
     */
    public Stratification(int stratumNumber) {
        this.strata = new ArrayList<>(stratumNumber);
        for (int i = 0; i < stratumNumber; i++) {
            this.strata.add(i, new ArrayList<>());
        }
    }

    /**
     * Adds a {@link Rule} to the specified stratum.
     *
     * @param stratum The stratum number.
     * @param rule    The rule to add.
     */
    public void addRule(int stratum, List<Rule> rule) {
        strata.get(stratum - 1).addAll(rule);
    }

    /**
     * Writes the stratification in the printstream provided.
     *
     * @param printStream the {@link PrintStream} where to write the stratification.
     */
    public void writeInFile(PrintStream printStream) {
        printStream.println(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strata.size(); i++) {
            stringBuilder.append("P").append(i + 1).append(" = {\n\t");
            stringBuilder.append(String.join("\n\t", strata.get(i).stream().map(Rule::toString).toList()));
            stringBuilder.append("\n}\n");
        }
        return stringBuilder.toString();
    }
}
