package com.fware.cspdt.cspm.core.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import lmf.formula.csp.node.Start;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;

/**
 * Essa classe contera os dados de analise do codigo.
 * 
 * A classe contem a ast do codigo e os conjuntos de funcoes,
 * processos, tipos e outras caracteristicas da linguagem CSP
 * 
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMModel {

	/**
	 * Faz referencia ao arquivo onde se encontra o codigo CSP
	 */
	public IFile file;

	/**
	 * Arvore sintatica (AST) da linguagem CSP
	 */
	public Start ast;
	
	/**
	 * Conjunto de referencias do modelo para o outline
	 */
	public final LinkedList<CspMRef> tocRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de canais do CSP
	 */
	public final LinkedList<CspMRef> channelRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de funcoes do CSP
	 */
	public final LinkedList<CspMRef> functionRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de processos do CSP
	 */
	public final LinkedList<CspMRef> processRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de constantes do CSP
	 */
	public final LinkedList<CspMRef> constantRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de tipos de dados do CSP
	 */
	public final LinkedList<CspMRef> datatypeRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de subtipos de dados do CSP
	 */
	public final LinkedList<CspMRef> subtypeRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de tipos nomeados do CSP
	 */
	public final LinkedList<CspMRef> nametypeRefs = new LinkedList<CspMRef>();
	/**
	 * Conjunto de referencias de abstracoes do CSP
	 */
	public final LinkedList<CspMRef> abstractRefs = new LinkedList<CspMRef>();

	// final LinkedList<CspMRef> links = new LinkedList<CspMRef>();
	/**
	 * Conjunto de definicoes de canais do CSP
	 */
	public final Map<String, String> channelDefinitions = new HashMap<String, String>();
	/**
	 * Conjunto de definicoes de funcoes do CSP
	 */
	public final Map<String, String> functionDefinitions = new HashMap<String, String>();
	/**
	 * Conjunto de definicoes de processos do CSP
	 */
	public final Map<String, String> processDefinitions = new HashMap<String, String>();
	/**
	 * Conjunto de definicoes de constantes do CSP
	 */
	public final Map<String, String> constantDefinitions = new HashMap<String, String>();
	/**
	 * Conjunto de definicoes de tipos de dados do CSP
	 */
	public final Map<String, String> datatypeDefinitions = new HashMap<String, String>();
	/**
	 * Conjunto de definicoes de subtipos de dados do CSP
	 */
	public final Map<String, String> subtypeDefinitions = new HashMap<String, String>();
	/**
	 * Conjunto de definicoes de tipos nomeados do CSP
	 */
	public final Map<String, String> nametypeDefinitions = new HashMap<String, String>();
	/**
	 * Conjunto de definicoes de abstracoes do CSP
	 */
	public final Map<String, String> abstractDefinitions = new HashMap<String, String>();

	public CspMModel(IFile file) {
		Assert.isNotNull(file);

		this.file = file;
	}
}