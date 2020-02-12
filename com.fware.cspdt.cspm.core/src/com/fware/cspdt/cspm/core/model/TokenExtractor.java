package com.fware.cspdt.cspm.core.model;

import lmf.formula.csp.analysis.ExtendedDepthFirstAdapter;
import lmf.formula.csp.node.Node;
import lmf.formula.csp.node.Token;

public class TokenExtractor extends ExtendedDepthFirstAdapter {

	private Token firstToken;
	private Token lastToken;

	public Token getFirstToken() {
		return firstToken;
	}
	
	public Token getLastToken() {
		return lastToken;
	}
	
	@Override
	public void defaultIn(Node node) {
		if (node instanceof Token) {
			if (firstToken == null)
				firstToken = (Token) node;

			lastToken = (Token) node;
		}
	}
}