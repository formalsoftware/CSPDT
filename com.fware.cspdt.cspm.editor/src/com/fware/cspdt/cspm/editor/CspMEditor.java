package com.fware.cspdt.cspm.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.fware.cspdt.cspm.core.model.CspMModel;
import com.fware.cspdt.cspm.core.parser.CspMParser;
import com.fware.cspdt.cspm.core.parser.CspMParserException;
import com.fware.cspdt.cspm.editor.config.CspMColorConstants;
import com.fware.cspdt.cspm.editor.config.CspMColorManager;
import com.fware.cspdt.cspm.editor.config.CspMSourceViewerConfiguration;
import com.fware.cspdt.cspm.editor.marker.CspMMarkingErrorHandler;
import com.fware.cspdt.cspm.editor.outline.CspMContentOutlinePage;
import com.fware.cspdt.cspm.editor.partition.CspMPartitionScanner;
import com.fware.cspdt.cspm.editor.scanner.CspMScanner;

import lmf.formula.csp.node.Start;
import lmf.formula.csp.semantic.exception.CspAnalyserException;

/**
 * Editor de texto para o CSPM.
 * 
 * Descreve o ambiente onde os codigos CSMP serao escritos
 * 
 * @author Joabe Jesus (jbjj@cin.ufpe.br)
 */
public class CspMEditor extends TextEditor implements IEditorPart {

	private static IPartitionTokenScanner cspPartitionScanner;
	private CspMColorManager colorManager;
	private ITokenScanner cspNotationScanner;
	private RuleBasedScanner cspCommentScanner;
	private RuleBasedScanner cspMultiLineCommentScanner;
	private CspMContentOutlinePage fOutlinePage;
	private IEditorInput input;	
	private CspMParser parser;
	private IDocument doc;
	StyleRange underLineRange = null;
	Start startNode = null;

	private CspMMarkingErrorHandler markingErrorHandler;

	/**
	 * Constructor.
	 */
	public CspMEditor() {
	}

	/**
	 * @see org.eclipse.ui.editors.text.TextEditor#initializeEditor()
	 */
	protected void initializeEditor() {
		super.initializeEditor();

		setSourceViewerConfiguration(new CspMSourceViewerConfiguration(this, getColorManager()));
	}

	public static IPartitionTokenScanner getCspPartitionScanner() {
		if (cspPartitionScanner == null) {
			cspPartitionScanner = new CspMPartitionScanner();
		}
		return cspPartitionScanner;
	}

	public CspMColorManager getColorManager() {
		if (colorManager == null) {
			colorManager = new CspMColorManager();
		}
		return colorManager;
	}

	public ITokenScanner getCspMScanner() {
		if (cspNotationScanner == null) {
			cspNotationScanner = new CspMScanner(getColorManager());
		}
		return cspNotationScanner;
	}

	public ITokenScanner getCspCommentScanner() {
		if (cspCommentScanner == null) {
			cspCommentScanner = new RuleBasedScanner();

			// Token that defines how to color single line comments.
			IToken cspCommentToken = new Token(new TextAttribute(getColorManager().getColor(CspMColorConstants.COMMENT.getCor())));

			cspCommentScanner.setRules(new IRule[] { new EndOfLineRule("--", cspCommentToken) });
		}
		return cspCommentScanner;
	}

	public ITokenScanner getCspMultiLineCommentScanner() {
		if (cspMultiLineCommentScanner == null) {
			cspMultiLineCommentScanner = new RuleBasedScanner();

			// Token that defines how to color multi-line comments.
			IToken cspMultiLineCommentToken = new Token(new TextAttribute(getColorManager().getColor(CspMColorConstants.MULTILINE_COMMENT.getCor())));

			cspMultiLineCommentScanner.setRules(new IRule[] { new MultiLineRule("{-", "-}", cspMultiLineCommentToken) });
		}
		return cspMultiLineCommentScanner;
	}

	/**
	 * @see org.eclipse.ui.editors.text.TextEditor#getAdapter()
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			return getOutlinePage();
		}

		return super.getAdapter(required);
	}

	private CspMContentOutlinePage getOutlinePage() {
		if (fOutlinePage == null) {
			fOutlinePage = new CspMContentOutlinePage(this);			
		}
		return fOutlinePage;
	}

	public IDocument getDocument() {
		return getSourceViewer().getDocument();
	}

	/**
	 * @see org.eclipse.ui.editors.text.TextEditor#dispose()
	 */
	public void dispose() {
		colorManager.dispose();
		cspNotationScanner = null;
		cspCommentScanner = null;
		cspMultiLineCommentScanner = null;

		super.dispose();
	}

	public ISourceViewer getViewer() {
		return getSourceViewer();
	}

	public StyleRange getUnderLineRange() {
		return underLineRange;
	}

	public void setUnderLineRange(StyleRange underLineRange) {
		this.underLineRange = underLineRange;
	}

	public CspMParser getParser() {
		return new CspMParser();
	}

	/**
	 * Metodo responsavel para a geracao da arvore sintatica (AST).
	 * 
	 * Este metodo executa o getInfo() do CspMParser que gera a AST
	 * e tambem faz a geracao de marcadores de erro no codigo.
	 * 
	 * @return O modelo criado pela analise do codigo
	 */
	public CspMModel parse() {
		CspMMarkingErrorHandler markingErrorHandler = getMarkingErrorHandler();
		try {
			markingErrorHandler.removeExistingMarkers();			
			return this.getParser().getInfo(getInputFile(), markingErrorHandler);
		} catch (CspMParserException e) {
			CspMEditorPlugin.log(e.getMessage(), e);
		} catch (CspAnalyserException e) {
			CspMEditorPlugin.log(e.getMessage(), e);
		}
		return null;
	}

	public CspMMarkingErrorHandler getMarkingErrorHandler() {
		return markingErrorHandler != null ? markingErrorHandler
				: (markingErrorHandler = new CspMMarkingErrorHandler(this));
	}

	public void doRevertToSaved() {
		super.doRevertToSaved();
		updateOutline();
		//parse();
	}

	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
		updateOutline();
		//parse();
	}

	public void doSaveAs() {
		super.doSaveAs();
		updateOutline();
		//parse();
	}

	public void doSetInput(IEditorInput input) throws CoreException {
		super.doSetInput(input);
		this.input = input;
		updateOutline();
		//parse();
	}

	private void updateOutline() {
		getOutlinePage().update();
	}

	public IEditorInput getInput() {
		return input;
	}

	protected IDocument getInputDocument() {
		IDocument document = getDocumentProvider().getDocument(input);
		return document;
	}

	public IFile getInputFile() {
		IFileEditorInput ife = (IFileEditorInput) input;
		IFile file = ife.getFile();
		return file;
	}
	
}