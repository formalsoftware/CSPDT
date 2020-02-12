package com.fware.cspdt.cspm.editor.config;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
/**
 * Esta classe mapea cores RGB para o tipo de cor utilizado pelo Eclipse.
 * 
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public final class CspMColorManager {

	private Map<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);

	/**
	 * 
	 * @param rgb
	 * @return
	 */
	public Color getColor(RGB rgb) {
		Color color = (Color) fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}

	/**
	 * 
	 * 
	 */
	public void dispose() {
		for (Color c : fColorTable.values()) {
			c.dispose();
		}
	}
}