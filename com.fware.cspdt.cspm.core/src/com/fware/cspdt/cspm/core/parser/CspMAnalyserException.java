package com.fware.cspdt.cspm.core.parser;

import lmf.formula.csp.node.Node;
import lmf.formula.csp.semantic.exception.CspAnalyserException;

public class CspMAnalyserException extends CspAnalyserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 87214992429334327L;

	public CspMAnalyserException(String msg, Node node) {
		super(node, msg);
	}

}
