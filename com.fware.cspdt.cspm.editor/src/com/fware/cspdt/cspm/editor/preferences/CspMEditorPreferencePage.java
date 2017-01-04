package com.fware.cspdt.cspm.editor.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
//import org.eclipse.jface.preference.BooleanFieldEditor;
//import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
//import org.eclipse.jface.preference.RadioGroupFieldEditor;
//import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.fware.cspdt.cspm.editor.CspMEditorPlugin;
/**
 * Esta classe foi criada com a finalidade de definir uma pagina no menu de preferencias do Eclipse.
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMEditorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public CspMEditorPreferencePage() {
		super(GRID);
		setPreferenceStore(CspMEditorPlugin.getDefault().getPreferenceStore());
		setDescription("CspM Preferences Page");
	}

	public void createFieldEditors() {
//		StringFieldEditor fieldEditor = new StringFieldEditor(CspMEditorPreferenceConstants.PREFIX_COLOR, "Folder Name: ", getFieldEditorParent());
//		addField(fieldEditor); 
		
		ColorFieldEditor defaultColor = new ColorFieldEditor(
				CspMEditorPreferenceConstants.COLOR_DEFAULT,
				"Default:",
		 		getFieldEditorParent());
			addField(defaultColor);
		
		ColorFieldEditor keywordColor = new ColorFieldEditor(
				CspMEditorPreferenceConstants.COLOR_KEYWORD,
				"Keywords:",
		 		getFieldEditorParent());
			addField(keywordColor);
			
		ColorFieldEditor processColor = new ColorFieldEditor(
				CspMEditorPreferenceConstants.COLOR_PROCESS,
				"Processes:",
		 		getFieldEditorParent());
			addField(processColor);	
			
		ColorFieldEditor intColor = new ColorFieldEditor(
				CspMEditorPreferenceConstants.COLOR_INT,
				"INT:",
		 		getFieldEditorParent());
			addField(intColor);	
			
		ColorFieldEditor commentColor = new ColorFieldEditor(
				CspMEditorPreferenceConstants.COLOR_COMMENT,
				"C&omments:",
		 		getFieldEditorParent());
			addField(commentColor);
			
		ColorFieldEditor multilineCommentColor = new ColorFieldEditor(
				CspMEditorPreferenceConstants.COLOR_MULTILINE_COMMENT,
				"Multiline C&omments:",
		 		getFieldEditorParent());
			addField(multilineCommentColor);	
	}

	
	public void init(IWorkbench workbench) {
	}
}