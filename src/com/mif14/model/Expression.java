package com.mif14.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Expression {

    private String predicate;
    private List<String> terms;
    private boolean isNegative;

    /**
     * Constructor.
     * Parses a string into a predicate followed by its parameters.
     * Also flags the expression as negative if it starts with "not".
     *
     * @param expression The expression to parse.
     */
    public Expression(String expression) {
        expression = removeDot(expression.trim());
        if (this.isNegative = expression.startsWith("not")) expression = removeNot(expression);
        String[] split = expression.split("\\s*\\(");
        String termsString = split[1].substring(0, split[1].length() - 1);
        String[] terms = termsString.replace(" ", "").split(",");

        this.predicate = split[0];
        this.terms = Arrays.asList(terms);
    }

    /**
     * Removes the dot at the end of the expression if present.
     *
     * @param expression The expression.
     * @return The expression without its dot.
     */
    private String removeDot(String expression) {
        if (expression.endsWith(".")) return expression.substring(0, expression.length() - 1);
        return expression;
    }

    /**
     * Removes the "not" from the expression if present.
     *
     * @param expression The expression.
     * @return The expression without the "not".
     */
    private String removeNot(String expression) {
        return expression.substring(3).trim();
    }

    /**
     * Get the expression's predicate.
     *
     * @return The predicate.
     */
    public String getPredicate() {
        return predicate;
    }

    /**
     * Get the terms of the expression.
     *
     * @return A list of terms.
     */
    public List<String> getTerms() {
        return terms;
    }

    /**
     * @return True if negative, false otherwise.
     */
    public boolean isNegative() {
        return this.isNegative;
    }

    @Override
    public String toString() {
        return (isNegative ? "not " : "") +
                predicate + "(" + String.join(", ", terms) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return Objects.equals(predicate, that.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicate);
    }
}
