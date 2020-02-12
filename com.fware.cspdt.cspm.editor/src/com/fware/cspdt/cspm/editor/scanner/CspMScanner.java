package com.fware.cspdt.cspm.editor.scanner;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

import com.fware.cspdt.cspm.editor.CspMEditorPlugin;
import com.fware.cspdt.cspm.editor.config.CspMColorManager;
import com.fware.cspdt.cspm.editor.config.Keywords;
import com.fware.cspdt.cspm.editor.preferences.CspMEditorPreferenceConstants;

/**
 * Nesta classe sao definidas as palavras chaves do CSPM.
 *
 * @author Joabe Jesus
 * @author Victor Vilmarques
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMScanner extends RuleBasedScanner {

	private WordRule processRule;
	private WordRule keywordRule;
	private IToken numberToken;
	private IToken keywordToken;
	private IToken processesToken;
	private IToken defaultToken;
	private static String[] PROCESSES = { "SKIP", "STOP" };
	private IPreferenceStore prefs;
	private CspMEditorPreferenceConstants colorConstant;
	
	public CspMScanner(CspMColorManager colorManager) {
		prefs = CspMEditorPlugin.getDefault().getPreferenceStore();
		// A token that defines how to color normal text.
		defaultToken = new Token(new TextAttribute(colorManager.getColor(PreferenceConverter.getColor(
				prefs, CspMEditorPreferenceConstants.COLOR_DEFAULT))));
		
		// A token that defines how to color numbers.
		numberToken = new Token(new TextAttribute(colorManager.getColor(PreferenceConverter.getColor(
				prefs, CspMEditorPreferenceConstants.COLOR_INT))));

		// A token that defines how to color keywords.
		keywordToken = new Token(new TextAttribute(colorManager.getColor(PreferenceConverter.getColor(
				prefs, CspMEditorPreferenceConstants.COLOR_KEYWORD)), null, SWT.BOLD));
		
		// A token that defines how to color predefined identifiers.
		processesToken = new Token(new TextAttribute(colorManager.getColor(PreferenceConverter.getColor(
				prefs, CspMEditorPreferenceConstants.COLOR_PROCESS)), null, SWT.ITALIC));
		
		keywordRule = new WordRule(new CspMKeywordDetector());
		Keywords[] values = Keywords.values();
		for (int i = 0; i < values.length; i++) {
			keywordRule.addWord(values[i].getValue(), keywordToken);
		}

		processRule = new WordRule(new CspMNameDetector());
		for (int i = 0; i < PROCESSES.length; i++) {
			processRule.addWord(PROCESSES[i], processesToken);
		}

		IRule[] rules = new IRule[] {
				new WhitespaceRule(new CspWhitespaceDetector()), 
				keywordRule, 
				processRule, 
				new NumberRule(numberToken) 
		};
		
		setDefaultReturnToken(defaultToken);
		setRules(rules);
	}
/**
 * Classe responsavel por adicionar um nome de processo, definido em codigo
 * ao conjunto de tokens conhecidos.
 * @param processName
 */
	public void addProcess(String processName) {
		if (processName != null && !"".equals(processName)) {
			processRule.addWord(processName, processesToken);
		}
	}
}