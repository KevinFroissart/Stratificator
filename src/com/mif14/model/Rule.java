package com.mif14.model;

import java.util.List;

public class Rule {
    private final Expression head;
    private final List<Expression> body;

    public Rule(Expression head, List<Expression> body) {
        this.head = head;
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder bodyString = new StringBuilder();
        for (Expression expression : this.body) {
            bodyString.append(expression.toString()).append(",");
        }
        bodyString.replace(bodyString.length() - 1, bodyString.length(), "");
        return head.toString() + " :- " + bodyString;
    }
}