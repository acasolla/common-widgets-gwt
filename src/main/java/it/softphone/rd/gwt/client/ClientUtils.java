package it.softphone.rd.gwt.client;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;


public class ClientUtils {

	public static String format(final String format, final Object... args) {
		  final RegExp regex = RegExp.compile("%[a-z]");
		  final SplitResult split = regex.split(format);
		  final StringBuffer msg = new StringBuffer();
		  for (int pos = 0; pos < split.length() - 1; pos += 1) {
		    msg.append(split.get(pos));
		    msg.append(args[pos].toString());
		  }
		  msg.append(split.get(split.length() - 1));
		  return msg.toString();
		}
	
	/**
	 * Truncate a string, to the given maximum value, adding three dots to the string.
	 * @param max the maximum length. Must be greater then 3, or an {@link IllegalArgumentException} is thrown
	 * @param value the value to truncate
	 * @return the truncated String
	 * @author Alessandro Casolla
	 */
	public static String truncate( int max, String value ){
		if ( max < 3 ) throw new IllegalArgumentException("max characters must be greater then 3 !");
		if ( value == null ) throw new IllegalArgumentException("value can't be null !");
		return value.length() > max ? value.substring( 0, max - 3 ).concat("...") : value;
	}
}
