package de.schmitzhermes.simpleclitool.util;


public class Util {
    public static String join(Iterable<? extends CharSequence> iterable, String separator) {
	StringBuilder sb = new StringBuilder();
	for (CharSequence s : iterable) {
	    sb.append(s);
	    sb.append(separator);
	}

	return sb.toString().substring(0, sb.toString().lastIndexOf(separator));
    }
}
 