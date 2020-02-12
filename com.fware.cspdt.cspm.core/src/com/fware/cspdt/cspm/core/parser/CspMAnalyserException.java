package com.fware.cspdt.cspm.core.parser;

import lmf.formula.csp.node.Node;
import lmf.formula.csp.semantic.exception.CspAnalyserException;
	/**
	 * Excecao disparada quando a analise lexica de um no na ast falha
	 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
	 *
	 */
public class CspMAnalyserException extends CspAnalyserException {

	/**
	 * Definir a serializacao da classe (compatibilidade)
	 */
	private static final long serialVersionUID = 87214992429334327L;
	/**
	 * Contrutor Padrao
	 * 
	 * @param msg A mensagem de erro
	 * @param node O no onde foi encontrado o erro
	 */
	public CspMAnalyserException(String msg, Node node) {
		super(node, msg);
	}

}
