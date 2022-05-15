package com.mif14.model;

import java.util.ArrayList;
import java.util.List;

public class Stratification {

    List<List<Rule>> strata;

    public Stratification(int stratumNumber) {
        this.strata = new ArrayList<>(stratumNumber);
        for (int i = 0; i < stratumNumber; i++) {
            this.strata.add(i, new ArrayList<>());
        }
    }

    public void addRule(int stratum, List<Rule> rule) {
        strata.get(stratum - 1).addAll(rule);
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
