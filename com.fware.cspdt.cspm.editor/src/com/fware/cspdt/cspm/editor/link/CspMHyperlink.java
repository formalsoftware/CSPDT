package com.fware.cspdt.cspm.editor.link;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;

import com.fware.cspdt.cspm.core.model.CspMRef;
import com.fware.cspdt.cspm.editor.CspMEditor;
/**
 * Esta classe define regioes de texto clicaveis que funcionam como link.
 * 
 * Ele utiliza os mapeamentos do CspMRef para encontrar a definicao
 * e abre o documento que possui aquela definicao.
 * 
 * @author Joabe Jesus
 * @author Victor Vilmarques
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMHyperlink implements IHyperlink {

	private CspMRef definitionRef;
	private CspMEditor editor;
	private IRegion region;

	public CspMHyperlink(CspMRef definitionRef, CspMEditor editor, IRegion region) {
		this.definitionRef = definitionRef;
		this.editor = editor;
		this.region = region;
	}	
	
	public CspMHyperlink(IRegion region) {
		this.region = region;
	}	

	public void open() {
		IDocument document = this.editor.getDocumentProvider().getDocument(this.editor.getEditorInput());

		if (this.definitionRef != null) {
			try {
				int beginElementOffset = document.getLineOffset(definitionRef.getLine() - 1) + definitionRef.getPos();
				if (beginElementOffset - 1 >= 0) {
					beginElementOffset--;
				}
				this.editor.selectAndReveal(beginElementOffset, definitionRef.getText().length());
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public IRegion getHyperlinkRegion() {
		return region;
	}

	public String getHyperlinkText() {
		return this.definitionRef.getText();
	}
	
	public String getTypeLabel() {
		return null;	
	}

	public String getText() {
		return this.definitionRef.getText();
	}
	
}