package com.mif14.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Expression {

    String predicate;
    List<String> terms;
    boolean isNegative;

    /**
     * Constructor with parameters
     *
     * @param isNegative true if the expression is negative else false.
     * @param predicate The predicate's name.
     * @param terms     The terms of the expression.
     */
    public Expression(boolean isNegative, String predicate, List<String> terms) {
        this.isNegative = isNegative;
        this.predicate = predicate;
        this.terms = terms;
    }

    public Expression(String expression) {
        expression = expression.trim();
        expression = removeDot(expression);
        this.isNegative = expression.startsWith("not");
        if (isNegative) expression = removeNot(expression);
        String[] split = expression.split("\\s*\\(");
        this.predicate = split[0];
        String termsString = split[1].substring(0, split[1].length() - 1);
        String[] terms = termsString.replace(" ", "").split(",");
        this.terms = Arrays.asList(terms);
    }

    private String removeDot(String line) {
        if (line.endsWith(".")) return line.substring(0, line.length() - 1);
        return line;
    }

    private String removeNot(String expression) {
        return expression.substring(3).trim();
    }

    public String getPredicate() {
        return predicate;
    }

    public List<String> getTerms() {
        return terms;
    }

    public boolean isNegative() {
        return this.isNegative;
    }

    @Override
    public String toString() {
        return (isNegative ? "not " : "") +
                predicate + "(" + String.join(", ", terms) + ")";
    }

    public boolean hasSamePredicate(Expression expression) {
        return this.predicate.equals(expression.predicate);
    }
}
