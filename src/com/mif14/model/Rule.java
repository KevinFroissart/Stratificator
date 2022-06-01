package com.mif14.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rule {

    private final Expression head;
    private final List<Expression> body;
    private final String IMPLICATION_PATTERN = "\\s*:-\\s*";

    /**
     * Constructor, creates a rule from a line parsed from {@link com.mif14.Parser}.
     *
     * @param line The line to read.
     */
    public Rule(String line) {
        line = removeDot(line.trim());
        String[] split = line.split(IMPLICATION_PATTERN);
        String body = split[1].substring(0, split[1].length() - 1);

        this.head = new Expression(split[0]);
        this.body = Arrays.stream(body.split("\\)\\s*,")).map(e -> e.endsWith(")") ? new Expression(e) : new Expression(e + ')')).collect(Collectors.toList());
    }

    /**
     * Get the head of the rule.
     *
     * @return An {@link Expression}.
     */
    public Expression getHead() {
        return head;
    }

    /**
     * Get the body of the rule.
     *
     * @return A list of {@link Expression}.
     */
    public List<Expression> getBody() {
        return body;
    }

    /**
     * Removes the dot at the end of the line.
     *
     * @param line The line to modify.
     * @return The modified line.
     */
    private String removeDot(String line) {
        if (line.endsWith(".")) return line.substring(0, line.length() - 1);
        return line;
    }

    @Override
    public String toString() {
        return "%s :- %s.".formatted(
                head.toString(),
                body.stream().map(Expression::toString).collect(Collectors.joining(", ")));
    }
}
