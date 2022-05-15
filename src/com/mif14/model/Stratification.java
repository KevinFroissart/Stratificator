package com.mif14.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Stratification {

    List<List<Rule>> strata;

    //TODO: ajouter doc
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
     * @param rule The rule to add.
     */
    public void addRule(int stratum, List<Rule> rule) {
        strata.get(stratum - 1).addAll(rule);
    }


    //TODO: ajouter doc (ou virer si pas nécessaire)
    public void writeInFile(String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(this.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: ajouter doc
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
