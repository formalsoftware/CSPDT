package com.fware.cspdt.cspm.editor;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import com.fware.cspdt.cspm.editor.partition.CspMPartitioner;
/**
 * Esta classe faz a representacao textual de um arquivo armazenado
 * no diretorio do projeto.
 * Atraves desta classe pode-se abrir o arquivo no editor realizar as edicoes
 * no seu conteudo e  monitorar todas as modificoes feitas desde seu carregamento.
 * 
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMDocumentProvider extends FileDocumentProvider {

	/**
	 * @see org.eclipse.ui.editors.text.FileDocumentProvider#createDocument()
	 */
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);

		if (document != null) {
			IDocumentPartitioner partitioner = new CspMPartitioner();
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}

		return document;
	}
}