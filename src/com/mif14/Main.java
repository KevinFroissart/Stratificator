package com.mif14;

import com.mif14.model.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String file = "src/com/mif14/resources/exemple1.dl";
        Program program = Parser.parse(file);
        program.printOutput();

        stratification(program);
    }

    public static void stratification(Program program) {
        Map<String, Integer> stratum = new HashMap<>();
        for (Expression predicate : program.getEdb()) {
            stratum.put(predicate.getPredicate(), 1);
        }
        for (Expression predicate : program.getRules().stream().map(Rule::getHead).toList()) {
            stratum.put(predicate.getPredicate(), 1);
        }
        System.out.println(stratum);
        Map<String, Integer> oldStratum;
        do {
            oldStratum = new HashMap<>(stratum);
            for (Rule rule : program.getRules()) {
                for (Expression subgoal : rule.getBody()) {
                    if (subgoal.isNegative()) {
                        stratum.put(rule.getHead().getPredicate(), Math.max(stratum.get(rule.getHead().getPredicate()), stratum.get(subgoal.getPredicate()) + 1));
                    } else {
                        stratum.put(rule.getHead().getPredicate(), Math.max(stratum.get(rule.getHead().getPredicate()), stratum.get(subgoal.getPredicate())));
                    }
                }
            }
        } while (!oldStratum.equals(stratum));
        System.out.println(stratum);
        /*
        for each predicate p do
            stratum[p] := 1;
        repeat
        for each rule r with head predicated p do begin
            for each negated subgoal of r with predicate q do
                stratum[p] := max(stratum[p], 1+stratum[q]);
            for each nonnegated subgoal of r with predicate q do
                stratum[p]:= max(stratum[p], stratum[q]);

            end
        until there are no changes to any stratum
        or some stratum exceeds the number of predicates
         */
    }
}
