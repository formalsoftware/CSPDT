package com.fware.cspdt.cspm.editor.partition;

import org.eclipse.jface.text.rules.FastPartitioner;

import com.fware.cspdt.cspm.editor.CspMEditor;

public class CspMPartitioner extends FastPartitioner {

	public CspMPartitioner() {
		super(CspMEditor.getCspPartitionScanner(), CspMPartitionScanner.PARTITION_TYPES);
	}
}