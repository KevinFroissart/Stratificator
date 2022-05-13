package com.mif14.model;

import java.util.List;

public class Expression {

	String predicate;
	List<String> terms;

	/**
	 * Empty constructor
	 */
	public Expression() {}

	/**
	 * Constructor with parameters
	 * @param predicate The predicate's name.
	 * @param terms The terms of the expression.
	 */
	public Expression(String predicate, List<String> terms) {
		this.predicate = predicate;
		this.terms = terms;
	}

	public String getPredicate() {
		return predicate;
	}

	public List<String> getTerms() {
		return terms;
	}
}
