package com.fware.cspdt.cspm.editor.scanner;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * @author Joabe Jesus
 * @author Victor Vilmarques
 */
public class CspWhitespaceDetector implements IWhitespaceDetector {

	/**
	 * Detects is the given character a white space character.
	 * 
	 * @param c
	 *            A character to test.
	 * 
	 * @return <code>true</code> if the character is a white space character,
	 *         <code>false</code> otherwise.
	 * 
	 * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
	 */
	public boolean isWhitespace(char c) {
		return Character.isWhitespace(c);
	}
}