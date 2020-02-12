package com.fware.cspdt.cspm.editor.config;
/**
 * Esta classe e uma enumeracao com as palavras reservadas do CSPM.
 * 
 * @author Joabe Jesus
 * @author Victor Vilmarques
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public enum Keywords {

	INCLUDE("include"), TRANSPARENT("transparent"), EXTERNAL("external"), DATATYPE("datatype"), SUBTYPE("subtype"), 
	NAMETYPE("nametype"), MODULE("module"), EXPORTS("exports"), ENDMODULE("endmodule"), INSTANCE("instance"), 
	CHANNEL("channel"), IF("if"), THEN("then"), ELSE("else"), LET("left"), WITHIN("within"), TRUE("true"), 
	FALSE("false"), ASSERT("assert"), PRINT("print");

	String value;

	Keywords(String v) {
		value = v;
	}

	public String getValue() {
		return value;
	}

}
