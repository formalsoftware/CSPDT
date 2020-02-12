package com.fware.cspdt.cspm.editor.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.fware.cspdt.cspm.editor.CspMEditorPlugin;
import com.fware.cspdt.cspm.editor.config.CspMColorConstants;

/**
 * Class used to initialize default preference values.
 *
 * @author Joabe Jesus
 * @author Victor Vilmarques
 */
public class CspMEditorPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 *      initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = CspMEditorPlugin.getDefault().getPreferenceStore();
		store.setDefault(CspMEditorPreferenceConstants.COLOR_DEFAULT, CspMColorConstants.DEFAULT.getCor().toString());
		store.setDefault(CspMEditorPreferenceConstants.COLOR_KEYWORD, CspMColorConstants.KEYWORD.getCor().toString());
		store.setDefault(CspMEditorPreferenceConstants.COLOR_PROCESS, CspMColorConstants.PROCESS.getCor().toString());
		store.setDefault(CspMEditorPreferenceConstants.COLOR_INT, CspMColorConstants.INT.getCor().toString());
		store.setDefault(CspMEditorPreferenceConstants.COLOR_COMMENT, CspMColorConstants.COMMENT.getCor().toString());
		store.setDefault(CspMEditorPreferenceConstants.COLOR_MULTILINE_COMMENT, CspMColorConstants.MULTILINE_COMMENT.getCor().toString());
	}
}