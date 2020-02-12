package com.fware.cspdt.cspm.core.parser;

import lmf.formula.csp.node.Node;
import lmf.formula.csp.semantic.exception.CspAnalyserException;

/**
 * Exceção disparada quando a análise léxica falha.
 *
 * @author Joabe Jesus
 * @author Victor Vilmarques
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 */
public class CspMAnalyserException extends CspAnalyserException {

	/**
	 * Definir a serialização da classe (compatibilidade)
	 */
	private static final long serialVersionUID = 87214992429334327L;

	/**
	 * Construtor Padrão
	 * 
	 * @param msg A mensagem de erro
	 * @param node O nó onde foi encontrado o erro
	 */
	public CspMAnalyserException(String msg, Node node) {
		super(node, msg);
	}

}
