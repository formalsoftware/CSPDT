package com.fware.cspdt.cspm.core.model;

public class CspMRef {

	private String text;

	private int line;
	private int pos;

	private CspMRef parent;

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