package com.fware.cspdt.cspm.editor.config;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
//import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlinkPresenter;
import org.eclipse.jface.text.hyperlink.URLHyperlinkDetector;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
//import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import com.fware.cspdt.cspm.editor.CspMEditor;
import com.fware.cspdt.cspm.editor.CspMEditorPlugin;
import com.fware.cspdt.cspm.editor.link.CspMHyperlinkDetector;
import com.fware.cspdt.cspm.editor.link.CspMHyperlinkPresenter;
//import com.fware.cspdt.cspm.editor.CspMEditorPlugin;
import com.fware.cspdt.cspm.editor.partition.CspMPartitionScanner;
import com.fware.cspdt.cspm.editor.preferences.CspMEditorPreferenceConstants;
//import com.fware.cspdt.cspm.editor.preferences.CspMEditorPreferenceConstants;
import com.fware.cspdt.cspm.editor.scanner.CspMScanner;

/**
 *  Nesta classe s�o definidas as configuracoes de vizualizacao do codigo.
 *  
 *  Ela quarda configuracoes do auto complete, sintax highlight entre outros.
 * 
 * @author Joabe Jesus
 * @author Victor Vilmarques
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMSourceViewerConfiguration extends TextSourceViewerConfiguration {

	private CspMEditor editor;
	private CspMDoubleClickStrategy doubleClickStrategy;
	private CspMScanner scanner;
	private CspMColorManager colorManager;
	private ContentAssistant contentAssistant;
	private NonRuleBasedDamagerRepairer nonRuledDamagerRep;
	private DefaultDamagerRepairer defaultDamagerRep;	
	private IPreferenceStore prefs;
	private CspMEditorPreferenceConstants colorConstant;
	private CspMProcessCompletionProcessor processCompletion;

	public CspMSourceViewerConfiguration(CspMEditor editor, CspMColorManager colorManager) {
		this.editor = editor;
		this.colorManager = colorManager;
		
		contentAssistant = new ContentAssistant();
	    contentAssistant.setContentAssistProcessor (new CspMProcessCompletionProcessor(editor), IDocument.DEFAULT_CONTENT_TYPE);
	    contentAssistant.enableAutoActivation(true);
	    contentAssistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_BELOW);
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return CspMPartitionScanner.PARTITION_TYPES;
	}

	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		CspMReconcilingStrategy strategy = new CspMReconcilingStrategy(editor);
		return new MonoReconciler(strategy, false);
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		prefs = CspMEditorPlugin.getDefault().getPreferenceStore();
		PresentationReconciler presentationRec = new PresentationReconciler();		
		
		defaultDamagerRep = new DefaultDamagerRepairer(getScanner());
		presentationRec.setDamager(defaultDamagerRep, CspMPartitionScanner.CSPM_DEFAULT_CONTENT_TYPE);
		presentationRec.setRepairer(defaultDamagerRep, CspMPartitionScanner.CSPM_DEFAULT_CONTENT_TYPE);
			
		nonRuledDamagerRep = new NonRuleBasedDamagerRepairer(new TextAttribute(getPreferedColor(prefs, CspMEditorPreferenceConstants.COLOR_COMMENT)));
		presentationRec.setDamager(nonRuledDamagerRep, CspMPartitionScanner.CSPM_COMMENT_CONTENT_TYPE);
		presentationRec.setRepairer(nonRuledDamagerRep, CspMPartitionScanner.CSPM_COMMENT_CONTENT_TYPE);
		
		nonRuledDamagerRep = new NonRuleBasedDamagerRepairer(new TextAttribute(getPreferedColor(prefs, CspMEditorPreferenceConstants.COLOR_MULTILINE_COMMENT)));
		presentationRec.setDamager(nonRuledDamagerRep, CspMPartitionScanner.CSPM_MULTILINE_COMMENT_CONTENT_TYPE);
		presentationRec.setRepairer(nonRuledDamagerRep, CspMPartitionScanner.CSPM_MULTILINE_COMMENT_CONTENT_TYPE);
		
		return presentationRec;
	}
	
	public Color getPreferedColor(IPreferenceStore prefs, String id) {
		return colorManager.getColor(PreferenceConverter.getColor(prefs, id));
	}

	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new CspMDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected CspMScanner getScanner() {
		if (scanner == null) {
			scanner = new CspMScanner(colorManager);

			//scanner.setDefaultReturnToken(new Token(new TextAttribute(getPreferedColor(prefs, CspMEditorPreferenceConstants.COLOR_DEFAULT))));
		}
		return scanner;
	}

	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		IAutoEditStrategy strategy = (IDocument.DEFAULT_CONTENT_TYPE.equals(contentType) ? new CspMAutoIndentStrategy()
				: new DefaultIndentLineAutoEditStrategy());
		return new IAutoEditStrategy[] { strategy };
	}

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {		
		
		contentAssistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));		

		return contentAssistant;
	}
	
	 @Override
	 public CspMHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		 CspMHyperlinkDetector[] detectors = new CspMHyperlinkDetector[] {
			 new CspMHyperlinkDetector(editor)
			 //new URLHyperlinkDetector()
		 };
		 return detectors;
	 }
	 
	public CspMHyperlinkPresenter getHyperlinkPresenter(ISourceViewer sourceViewer) {
		return new CspMHyperlinkPresenter(new RGB(0, 0, 255), editor);
	}
}