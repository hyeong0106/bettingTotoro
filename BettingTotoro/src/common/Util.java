package common;

public class Util {
	public static String encodeContent(String content) {
		String ret = content;
		try {
		ret = replace(ret, "&", "&amp;");
		ret = replace(ret, "<", "&lt;");
		ret = replace(ret, ">", "&gt;");
		} catch (NullPointerException e) {
		e.printStackTrace();
		}
		return ret;
		}

		public static String decodeContent(String content) {
		String ret = content;
		try {
		ret = replace(ret, "&amp;", "&");
		ret = replace(ret, "&lt;", "<");
		ret = replace(ret, "&gt;", ">");
		}
		catch (NullPointerException e){
		e.printStackTrace();
		}
		return ret;
		}
		
		public static String replace(String str, String pattern, String replace) {
			int s = 0;
			int e = 0;
			StringBuffer result = new StringBuffer();

			while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e+pattern.length();
			}
			result.append(str.substring(s));
			return result.toString();
			}
}
