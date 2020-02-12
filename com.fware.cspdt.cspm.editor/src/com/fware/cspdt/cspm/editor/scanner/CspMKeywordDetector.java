package com.fware.cspdt.cspm.editor.scanner;

import org.eclipse.jface.text.rules.IWordDetector;

public class CspMKeywordDetector implements IWordDetector {

	public boolean isWordStart(char c) {
		return Character.isLetter(c);
	}

	public boolean isWordPart(char c) {
		return Character.isLetter(c);
	}
}