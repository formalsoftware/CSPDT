package com.fware.cspdt.cspm.editor.outline;

import java.util.ArrayList;
import java.util.List;

import lmf.formula.csp.node.Node;

import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.TreeNode;
/**
 * Esta classe representa o no da arvore da classe CspMOutlineTreeLabelProvider.
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMOutlineTreeNode extends TreeNode {

	public final Position position;

	public final List<CspMOutlineTreeNode> childrenBackup;

	public CspMOutlineTreeNode(Node node, Position position) {
		super(node);

		this.position = position;
		this.childrenBackup = new ArrayList<CspMOutlineTreeNode>();
	}

	public void addChild(CspMOutlineTreeNode child) {
		childrenBackup.add(child);
	}

	void updateChildren() {
		this.setChildren(childrenBackup.toArray(new CspMOutlineTreeNode[childrenBackup.size()]));
	}
}