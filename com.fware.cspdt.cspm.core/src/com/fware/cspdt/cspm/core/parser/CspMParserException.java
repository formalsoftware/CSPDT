package com.fware.cspdt.cspm.core.parser;

public class CspMParserException extends Exception {
	
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private int linha;
	private int pos;
	private String token;

	public CspMParserException(String message, String token, int linha, int pos) {
		super(message);
		this.linha = linha;
		this.pos = pos;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public int getLinha() {
		return linha;
	}

	public int getPos() {
		return pos;
	}
	
}
