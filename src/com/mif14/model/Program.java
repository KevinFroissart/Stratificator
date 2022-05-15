package com.mif14.model;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private final List<Expression> edb;
	private final List<Rule> rules;

	public Program() {
		this.edb = new ArrayList<>();
		this.rules = new ArrayList<>();
	}

	public Program(List<Expression> edb, List<Rule> rules) {
		this.edb = edb;
		this.rules = rules;
	}

	public List<Expression> getEdb() {
		return edb;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void addEdb(Expression expression) {
		this.edb.add(expression);
	}

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	public void printOutput() {
		String output = "% EDB:\n";
		output += this.edb.stream().map(edb -> edb.toString() + ".\n").reduce("", String::concat);
		output += "\n\n% Rules:\n";
		output += this.rules.stream().map(rule-> rule.toString() + "\n").reduce("", String::concat);
		System.out.println(output);
	}
}
