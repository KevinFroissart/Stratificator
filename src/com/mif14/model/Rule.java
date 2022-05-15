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
        line = line.trim();
        line = removeDot(line);
        String IMPLICATION_PATTERN = "\\s*:-\\s*";
        String[] split = line.split(IMPLICATION_PATTERN);
        Expression head = new Expression(split[0]);
        String body = split[1].substring(0, split[1].length() - 1);
        List<Expression> rules = Arrays.stream(body.split("\\)\\s*,")).map(e -> e.endsWith(")") ? new Expression(e) : new Expression(e + ')')).collect(Collectors.toList());
        this.head = head;
        this.body = rules;
    }

    public Expression getHead() {
        return head;
    }

    public List<Expression> getBody() {
        return body;
    }

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
