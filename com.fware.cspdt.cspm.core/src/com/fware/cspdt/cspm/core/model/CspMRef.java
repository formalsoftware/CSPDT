package com.fware.cspdt.cspm.core.model;

/**
 * Essa classe representa uma referencia dentro do documento CSP.
 * 
 * Essa classe mostra o spelling de um token e a linha e coluna
 * em que ele se encontra. Tambem traz uma referencia ao seu
 * elemento pai na hierarquia da AST.
 * 
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMRef {

	private String text;

	private int line;
	private int pos;

	private CspMRef parent;

	/**
	 * Um construtor padrao para uma referencia no documento.
	 * 
	 * @param text O spelling do token sendo referenciado
	 * @param line A linha em que se encontra
	 * @param pos A coluna em que esta (posicao dentro da linha)
	 * @param parent O elemento pai na AST
	 */
	public CspMRef(String text, int line, int pos, CspMRef parent) {
		this.text = text;
		this.line = line;
		this.pos = pos;
		this.parent = parent;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public CspMRef getParent() {
		return parent;
	}

	public void setParent(CspMRef parent) {
		this.parent = parent;
	}
}