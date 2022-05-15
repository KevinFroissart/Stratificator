package com.mif14.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Stratifier {

    /**
     * Computes a stratified version of a given {@link Program}.
     *
     * @param program The {@link Program} to startify.
     * @return a computed {@link Stratification}.
     */
    public static Stratification stratificate(Program program) {
        Map<String, Integer> stratum = new HashMap<>();
        for (Expression predicate : program.getRules().stream().map(Rule::getHead).toList()) {
            stratum.put(predicate.getPredicate(), 1);
        }
        Map<String, Integer> oldStratum;
        do {
            oldStratum = new HashMap<>(stratum);
            for (Rule rule : program.getRules()) {
                for (Expression expression : rule.getBody()) {
                    if (program.getEdb().contains(expression)) continue;
                    if (expression.isNegative()) {
                        stratum.put(rule.getHead().getPredicate(), Math.max(stratum.get(rule.getHead().getPredicate()), stratum.get(expression.getPredicate()) + 1));
                    } else {
                        stratum.put(rule.getHead().getPredicate(), Math.max(stratum.get(rule.getHead().getPredicate()), stratum.get(expression.getPredicate())));
                    }
                }
            }
        } while (!oldStratum.equals(stratum));
        int stratumNumber = stratum.values().stream().max(Comparator.naturalOrder()).orElse(1);
        Stratification stratification = new Stratification(stratumNumber);
        for (Map.Entry<String, Integer> entry : stratum.entrySet()) {
            stratification.addRule(entry.getValue(), program.getRules()
                    .stream()
                    .filter(rule -> rule.getHead().getPredicate().equals(entry.getKey()))
                    .toList());
        }
        return stratification;
    }
}
