package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {
	private static List<String>	keys	= new ArrayList<String>();
	static {
		keys.add("and");
		keys.add("exec");
		keys.add("insert");
		keys.add("select");
		keys.add("delete");
		keys.add("update");
		keys.add("count");
		keys.add("*");
		keys.add("%");
		keys.add("chr");
		keys.add("mid");
		keys.add("master");
		keys.add("truncate");
		keys.add("char");
		keys.add("declare");
		keys.add(";");
		keys.add("or");
		keys.add("");
		keys.add("+");
		keys.add("-");
		keys.add("<");
		keys.add(">");
		keys.add(",");
	}

	public static boolean notEmpty(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	public static String stringVerification(String vs) {
		if (vs != null) {
			// return vs.replaceAll("[^a-zA-Z0-9]", "");
			return vs;
		}
		return null;
	}

	public static boolean sqlFilter(String str) {
		return keys.contains(str);
	}

	/**
	 * 将字符串根据分隔符拆分为数组，并把每个元素转换为int
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static int[] StringToIntArray(String str, String split) {
		if (notEmpty(str) && notEmpty(split)) {
			String[] sa = str.split(split);
			int len = sa.length;
			int[] retVal = new int[len];
			for (int i = 0; i < len; i++) {
				retVal[i] = Integer.valueOf(sa[i]);
			}
			return retVal;
		}
		return new int[0];
	}

	/**
	 * 数组转换为字符串
	 * 
	 * @param objs
	 * @param split
	 * @return
	 */
	public static String arrayToString(Object[] objs, String split) {
		if (objs != null && objs.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < objs.length; i++) {
				sb.append(objs[i].toString());
				if (i < objs.length - 1) {
					sb.append(split);
				}
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 
	 * @param objs
	 * @param split
	 *            分隔符
	 * @param before
	 *            每个元素开头增加的字符
	 * @param end
	 *            每个元素结尾增加的字符
	 * @return
	 */
	public static String arrayToString(Object[] objs, String split, String before, String end) {
		if (objs != null && objs.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < objs.length; i++) {
				sb.append(before);
				sb.append(objs[i].toString());
				sb.append(end);
				if (i < objs.length - 1) {
					sb.append(split);
				}
			}
			return sb.toString();
		}
		return null;
	}

	public static String dateStringFormat(String date) {
		String[] ds = date.split("-");
		date = ds[0];
		date += "-";
		if (ds[1].startsWith("0")) {
			ds[1] = ds[1].substring(1, ds[1].length());
		}
		if (ds[2].startsWith("0")) {
			ds[2] = ds[2].substring(1, ds[2].length());
		}
		date += ds[1];
		date += "-";
		date += ds[2];

		return date;
	}

	/**
	 * 格式化数字不足补齐
	 * 
	 * @param num
	 * @param value
	 * @return
	 */
	public static String format0String(int num, long value) {
		String result = (new Long(value)).toString();
		while (num > result.length()) {
			result = "0" + result;
		}
		return result;
	}

	public static void main(String[] args) {
		StringUtil.dateStringFormat("2011-12-19");
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}
}
