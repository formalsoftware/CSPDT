package com.fware.cspdt.cspm.core.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;

import org.eclipse.core.resources.IFile;

import com.fware.cspdt.cspm.core.model.CspMModel;

import lmf.formula.csp.lexer.Lexer;
import lmf.formula.csp.node.Start;
import lmf.formula.csp.node.Token;
import lmf.formula.csp.parser.Parser;
import lmf.formula.csp.parser.ParserException;
import lmf.formula.csp.semantic.CspAnalyserListener;
import lmf.formula.csp.semantic.CspContextualAnalyser;
import lmf.formula.csp.semantic.exception.CspAnalyserException;

public class CspMParser {

	private CspMModel info;

	public CspMModel getInfo(IFile file, CspAnalyserListener analyser) throws CspMParserException, CspAnalyserException {
    	if (info == null) {
    		return info = parse(file, analyser, true, false);
    	}
    	
		return info;
	}

	public CspMModel parse(IFile file, CspAnalyserListener listener, boolean stopOnError, boolean debugging) {
		CspMModel m = new CspMModel(file);

		try {
			// Parsing
			InputStream in = file.getContents();			
			InputStreamReader isReader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isReader);
	
			//Lexer lexer = new Lexer(new PushbackReader(new BufferedReader(new FileReader(file.getName())),1024));
			Parser parser = new Parser(new Lexer(new PushbackReader(br, 5000)));
			Start start = parser.parse();
	
			// Contextual Analysis
			CspContextualAnalyser analyser = new CspContextualAnalyser(listener);
			analyser.setStopOnError(stopOnError);
			analyser.setDebugging(debugging);
			start.apply(analyser);
			
//		 javax.swing.JTree treeView = new javax.swing.JTree();
//		 start.apply(new CspMTreeGenerator(treeView));
//		 javax.swing.JFrame jFrame = new javax.swing.JFrame("Test");
//		 jFrame.setBounds(0, 0, 400, 600);
//		 jFrame.getContentPane().add(new javax.swing.JScrollPane(treeView));
//		 jFrame.setVisible(true);

			m.ast = start;
		
		} catch (ParserException e) {
			Token token = e.getToken();
			if (listener != null)
				listener.catchException(new CspMAnalyserException(e.getMessage(), token));
			else
				throw new RuntimeException("Listener is undefined.");
		} catch (Exception e) {
			if (listener != null)
				listener.catchException(new CspMAnalyserException(e.getMessage(), null));
			else
				throw new RuntimeException("Listener is undefined.");
		}

		return m;
	}
}