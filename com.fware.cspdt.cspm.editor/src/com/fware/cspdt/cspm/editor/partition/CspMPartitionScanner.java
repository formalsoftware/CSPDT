package com.fware.cspdt.cspm.editor.partition;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
/**
 * Esta classe monta a estrategia de acao para realizar o escaneamento do documento por tokens e suas particoes. 
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMPartitionScanner extends RuleBasedPartitionScanner {

	public static final String CSPM_DEFAULT_CONTENT_TYPE = IDocument.DEFAULT_CONTENT_TYPE;
	public static final String CSPM_COMMENT_CONTENT_TYPE = "__csp_comment_content_type";
	public static final String CSPM_MULTILINE_COMMENT_CONTENT_TYPE = "__csp_multicomment_content_type";

	public static final String[] PARTITION_TYPES = new String[] { 
			CSPM_DEFAULT_CONTENT_TYPE, 
			CSPM_COMMENT_CONTENT_TYPE,
			CSPM_MULTILINE_COMMENT_CONTENT_TYPE };

	public CspMPartitionScanner() {
		//IToken defaultToken = new Token(CSPM_DEFAULT_CONTENT_TYPE);
		IToken commentToken = new Token(CSPM_COMMENT_CONTENT_TYPE);
		IToken multiLineCommentToken = new Token(CSPM_MULTILINE_COMMENT_CONTENT_TYPE);

		IPredicateRule[] predicateRules = new IPredicateRule[] {
			// Add rule for single line comments.
			new EndOfLineRule("--", commentToken),
	
			// Add rules for multi-line comments and javadoc.
			new MultiLineRule("{-", "-}", multiLineCommentToken) };

		setPredicateRules(predicateRules);
	}
}