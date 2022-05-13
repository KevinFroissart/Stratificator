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
	 * @param predicate The predicate's name.
	 * @param terms The terms of the expression.
	 */
	public Expression(String predicate, List<String> terms) {
		this.isNegative = false;
		this.predicate = predicate;
		this.terms = terms;
	}

	public Expression(String expression) {
		this.isNegative = expression.startsWith("not");
		String[] split = expression.split("\\(");
		String predicate = split[0];
		String termsString = split[1].substring(0, split[1].length() - 2);
		String[] terms = termsString.split(",");
		this.predicate = predicate;
		this.terms = Arrays.asList(terms);
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
		return predicate + "(" + terms.stream().collect(Collectors.joining(",")) + ")";
	}
}
