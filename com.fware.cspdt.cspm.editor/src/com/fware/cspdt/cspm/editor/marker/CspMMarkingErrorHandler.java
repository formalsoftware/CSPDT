package com.fware.cspdt.cspm.editor.marker;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import com.fware.cspdt.cspm.editor.CspMEditor;
import com.fware.cspdt.cspm.editor.CspMEditorPlugin;

import lmf.formula.csp.node.Node;
import lmf.formula.csp.semantic.CspAnalyserListener;
import lmf.formula.csp.semantic.exception.CspAnalyserException;
import lmf.formula.csp.util.CspNodeLocator;
import lmf.formula.csp.util.Location;
/**
 * Nesta classe cont�m a  configuracao da marcacao de erros no codigo.
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMMarkingErrorHandler implements CspAnalyserListener {

	public static final String ERROR_MARKER_ID = CspMEditorPlugin.PLUGIN_ID + ".cspmproblem";

	private IFile file;
	private IDocument document;

	private CspMEditor editor;

	public CspMMarkingErrorHandler(CspMEditor editor) {
		this.editor = editor;
		IEditorInput editorInput = editor.getEditorInput();
		file = ((IFileEditorInput) editorInput).getFile();
		document = editor.getDocumentProvider().getDocument(editorInput);
	}

	/**
	 * Este metodo tem a funcao de apagar todos os marcadores atuais no codigo.
	 */
	public void removeExistingMarkers() {
		try {
			//file.deleteMarkers(ERROR_MARKER_ID, true, IResource.DEPTH_ZERO);
			file.deleteMarkers(null, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
	}

//	public void markErrors(boolean isFatal) {
//		try {
//			CspMModel info = this.editor.parse();
//		} catch (CspMParserException e) {
//			Map map = new HashMap();
//			int lineNumber = e.getLinha();
//			int columnNumber = e.getPos();
//			MarkerUtilities.setLineNumber(map, lineNumber);
//			MarkerUtilities.setMessage(map, e.getMessage());
//			map.put(IMarker.LOCATION, file.getFullPath().toString());
//
//			Integer charStart = getCharStart(lineNumber, columnNumber);
//			if (charStart != null)
//				map.put(IMarker.CHAR_START, charStart);
//
//			Integer charEnd = getCharEnd(lineNumber, columnNumber);
//			if (charEnd != null)
//				map.put(IMarker.CHAR_END, charEnd);
//
//			map.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
//
//			try {
//				MarkerUtilities.createMarker(file, map, ERROR_MARKER_ID);
//			} catch (CoreException ee) {
//				ee.printStackTrace();
//			}
//		}
//	}

	private Integer getCharEnd(int lineNumber, int columnNumber) {
		try {
			return new Integer(document.getLineOffset(lineNumber - 1) + columnNumber);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Integer getCharStart(int lineNumber, int columnNumber) {
		try {
			int lineStartChar = document.getLineOffset(lineNumber - 1);
			Integer charEnd = getCharEnd(lineNumber, columnNumber);
			if (charEnd != null) {
				ITypedRegion typedRegion = document.getPartition(charEnd.intValue() - 2);
				int partitionStartChar = typedRegion.getOffset();
				return new Integer(partitionStartChar);
			} else
				return new Integer(lineStartChar);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void started() {
	}

	public void analysed(Node node) {
	}

	public void analysing(Node node) {
	}

	public void finished() {
	}

	public void catchException(CspAnalyserException e) {
		Location location = CspNodeLocator.getLocation(e.getNode());
		int offset = getOffset(location.getLine() - 1) + location.getPos() - 1;
		addProblemMarker(e.getMessage(), offset, offset, IMarker.SEVERITY_ERROR);
	}

	public void warning(int line, int pos, String msg) {
		int offset = getOffset(line - 1) + pos - 1;
		addProblemMarker(msg, offset, offset, IMarker.SEVERITY_WARNING);
	}
	
	private void addProblemMarker(String msg, int offset, int length, int severity) {
		FileEditorInput fileInput = (FileEditorInput) editor.getEditorInput();
		try {
			IMarker marker = fileInput.getFile().createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.SEVERITY, severity);
			marker.setAttribute(IMarker.MESSAGE, msg);
			marker.setAttribute(IMarker.CHAR_START, offset);
			marker.setAttribute(IMarker.CHAR_END, length);
			marker.setAttribute(IMarker.SOURCE_ID, CspMMarkingErrorHandler.ERROR_MARKER_ID);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
	}
	
	private int getOffset(int line) {
		try {
			return editor.getDocument().getLineOffset(line);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return 0;
		}
	}
}