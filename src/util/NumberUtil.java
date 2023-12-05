package util;

import java.util.regex.Pattern;

public class NumberUtil {
	private static Pattern pattern = Pattern.compile("[0-9]*");
	/**
	 * 判断字符串是否为数字
	 * @param str 
	 * @return
	 */
	public static boolean isNumeric(String str) {
		return pattern.matcher(str).matches();
	}
}
