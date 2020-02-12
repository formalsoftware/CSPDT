package com.fware.cspdt.cspm.editor.preferences;

import com.fware.cspdt.cspm.editor.CspMEditorPlugin;
import com.fware.cspdt.cspm.editor.config.CspMColorConstants;

/**
 * Constant definitions for plug-in preferences.
 *
 * @author Joabe Jesus
 * @author Victor Vilmarques
 */
public interface CspMEditorPreferenceConstants {

	public static final String PREFIX = CspMEditorPlugin.PLUGIN_ID + ".";
	public static final String PREFIX_COLOR = PREFIX + "color.";
	public static final String COLOR_DEFAULT = PREFIX_COLOR + CspMColorConstants.DEFAULT;
	public static final String COLOR_KEYWORD = PREFIX_COLOR + CspMColorConstants.KEYWORD;
	public static final String COLOR_PROCESS = PREFIX_COLOR + CspMColorConstants.PROCESS;
	public static final String COLOR_INT = PREFIX_COLOR + CspMColorConstants.INT;
	public static final String COLOR_COMMENT = PREFIX_COLOR + CspMColorConstants.COMMENT;
	public static final String COLOR_MULTILINE_COMMENT = PREFIX_COLOR + CspMColorConstants.MULTILINE_COMMENT;

}
