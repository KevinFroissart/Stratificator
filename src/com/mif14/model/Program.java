package com.mif14.model;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private List<Expression> edb;
	private List<Rule> rules;


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
}
