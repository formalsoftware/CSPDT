package com.fware.cspdt.cspm.editor.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;

import com.fware.cspdt.cspm.editor.CspMEditor;

/**
 * Esta classe cria uma estrategia de acao para a representacao do texto na presenca de mudancas no codigo CSPM.
 * 
 * @author Joabe Jesus
 * @author Victor Vilmarques
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMReconcilingStrategy implements IReconcilingStrategy {

	private CspMEditor editor;

	private IDocument fDocument;

	/** holds the calculated positions */
	protected final List<Position> fPositions = new ArrayList<Position>();

	/** The offset of the next character to be read */
	protected int fOffset;

	/** The end offset of the range to be scanned */
	protected int fRangeEnd;

	public CspMReconcilingStrategy(CspMEditor editor) {
		this.editor = editor;
	}

	public CspMEditor getEditor() {
		return editor;
	}

	/**
	 * Verifica inicio e fim da mudanca no texto.
	 */
	public void initialReconcile() {
		fOffset = 0;
		fRangeEnd = fDocument.getLength();
		calculatePositions();
	}

	@Override
	public void setDocument(IDocument document) {
		this.fDocument = document;
	}

	@Override
	public void reconcile(DirtyRegion arg0, IRegion arg1) {
		initialReconcile();
	}

	@Override
	public void reconcile(IRegion arg0) {
		initialReconcile();
	}

	private void calculatePositions() {
		// TODO calculatePositions()
	}
}