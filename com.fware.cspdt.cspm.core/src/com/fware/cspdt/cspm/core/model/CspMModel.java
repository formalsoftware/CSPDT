package com.fware.cspdt.cspm.core.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import lmf.formula.csp.node.Start;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;

public class CspMModel {

	public IFile file;

	public Start ast;
	
	public final LinkedList<CspMRef> tocRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> channelRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> functionRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> processRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> constantRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> datatypeRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> subtypeRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> nametypeRefs = new LinkedList<CspMRef>();
	public final LinkedList<CspMRef> abstractRefs = new LinkedList<CspMRef>();

	// final LinkedList<CspMRef> links = new LinkedList<CspMRef>();

	public final Map<String, String> channelDefinitions = new HashMap<String, String>();
	public final Map<String, String> functionDefinitions = new HashMap<String, String>();
	public final Map<String, String> processDefinitions = new HashMap<String, String>();
	public final Map<String, String> constantDefinitions = new HashMap<String, String>();
	public final Map<String, String> datatypeDefinitions = new HashMap<String, String>();
	public final Map<String, String> subtypeDefinitions = new HashMap<String, String>();
	public final Map<String, String> nametypeDefinitions = new HashMap<String, String>();
	public final Map<String, String> abstractDefinitions = new HashMap<String, String>();

	public CspMModel(IFile file) {
		Assert.isNotNull(file);

		this.file = file;
	}
}