package com.mif14.model;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private final List<Expression> edb;
	private final List<Rule> rules;

	/**
	 * Constructor.
	 * Creates an empty EDB list and an empty rule list.
	 */
	public Program() {
		this.edb = new ArrayList<>();
		this.rules = new ArrayList<>();
	}

	/**
	 * Get the list EDB.
	 *
	 * @return A list of {@link Expression}.
	 */
	public List<Expression> getEdb() {
		return edb;
	}

	/**
	 * Get the list of rules.
	 *
	 * @return A list of {@link Rule}.
	 */
	public List<Rule> getRules() {
		return rules;
	}

	/**
	 * Adds an {@link Expression} to the EDB list.
	 *
	 * @param expression The {@link Expression} to add.
	 */
	public void addEdb(Expression expression) {
		this.edb.add(expression);
	}

	/**
	 * Adds a {@link Rule} to the list of rules.
	 *
	 * @param rule The {@link Rule} to add.
	 */
	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	/**
	 * Prints the EDBs and the rules.
	 */
	public void printOutput() {
		String output = "% EDB:\n";
		output += this.edb.stream().map(edb -> edb.toString() + ".\n").reduce("", String::concat);
		output += "\n% Rules:\n";
		output += this.rules.stream().map(rule -> rule.toString() + "\n").reduce("", String::concat);
		System.out.println(output);
	}
}
