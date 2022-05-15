package com.mif14.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rule {

    private final Expression head;
    private final List<Expression> body;

    /**
     * Constructor with parameters
     *
     * @param head the head of the rule
     * @param body the body of the rule
     */
    public Rule(Expression head, List<Expression> body) {
        this.head = head;
        this.body = body;
    }

    /**
     * Constructor, creates a rule from a line parsed from {@link com.mif14.Parser}
     *
     * @param line the line to read
     */
    public Rule(String line) {
        String[] split = line.split(" :- ");
        Expression head = new Expression(split[0]);
        String body = split[1].substring(0, split[1].length() - 1);
        List<Expression> rules = Arrays.stream(body.split("\\)\\s*,")).map(e -> e.endsWith(")") ? new Expression(e) : new Expression(e + ')')).collect(Collectors.toList());
        this.head = head;
        this.body = rules;
    }

    @Override
    public String toString() {
        return "%s :- %s.".formatted(
                head.toString(),
                body.stream().map(Expression::toString).collect(Collectors.joining(", ")));
    }
}
