package avajlauncher.variables;

import java.util.regex.Pattern;

public class Variables {

	public int				lineNumber;
	public Pattern			positiveIntPattern;
	public Pattern			aircraftStrParamPattern;
	
	public Variables() {
		this.lineNumber = 0;
		positiveIntPattern = Pattern.compile("\\+?\\d+");
		aircraftStrParamPattern = Pattern.compile("\\b(?!\\d+\\b)\\w+\\b");
	}
}
