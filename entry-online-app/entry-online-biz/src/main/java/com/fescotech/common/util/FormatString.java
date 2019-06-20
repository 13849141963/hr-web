/**
 * Copyright 2002-2010 the original author or authors.
 */
package com.fescotech.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串格式化类
 *   
 * @author Ray Niu
 */
public class FormatString {

	public FormatString() {

	}

	public static String getWhereClauseByIds(List<String> ids, String fieldName) {
		if (Validator.isEmpty(ids))
			return null;
		StringBuilder stringBuilder = new StringBuilder();
		List<String> sqls = new ArrayList<String>();
		for (int i = 0; i < ids.size(); i++) {
			stringBuilder.append("'").append(ids.get(i)).append("',");
			if ((i + 1) % 1000 == 0) {
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
				sqls.add(stringBuilder.toString());
				stringBuilder = new StringBuilder();
			}
		}
		if (stringBuilder.length() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			sqls.add(stringBuilder.toString());
		}
		StringBuilder sqlBufWhere = new StringBuilder(" " + fieldName + " IN ");
		for (int i = 0; i < sqls.size(); i++) {
			String str = sqls.get(i);
			sqlBufWhere.append("(" + str + ")");
			if (i + 1 < sqls.size()) {
				sqlBufWhere.append(" OR " + fieldName + " IN ");
			}
		}
		return sqlBufWhere.toString();
	}

	/**
	 * 根据资源库ID，以及版本，获取文件在版本库的内部文件名。
	 * 
	 * @param uuid    资源库ID
	 * @param version 版本
	 * @return        版本库的内部文件名
	 */
	public static String getFileNameByNodePathTemplate(String uuid, String version) {
		if (Validator.isNull(uuid))
			return null;
		StringBuilder nodeFolderPath = new StringBuilder(buildNodeFolderPath(uuid));
		String internalFileName = nodeFolderPath.substring(nodeFolderPath
				.lastIndexOf(File.separator) + 1);

		if (Validator.isNotNull(version)) {
			internalFileName += "_" + version;
		}
		return internalFileName;
	}

	/**
	 * 根据模版将给定的uuid，转换为文件夹路径，该路径包含文件名（文件名不包含版本）。<br>
	 * 当前的模版为：xx/xx/xxxxxxxxxxxxxxxxxxxxxxxxxxxx。 
	 * 
	 * @param uuid 资源库ID
	 * @return     文件夹路径，该路径包含文件名。
	 */
	public static String buildNodeFolderPath(String uuid) {
		StringBuffer sb = new StringBuffer();
		char[] chars = uuid.toCharArray();
		int cnt = 0;
		for (int i = 0; i < StringPool.NODE_PATH_TEMPLATE.length(); i++) {
			char ch = StringPool.NODE_PATH_TEMPLATE.charAt(i);
			if (ch == 'x' && cnt < chars.length) {
				ch = chars[cnt++];
				if (ch == '-') {
					ch = chars[cnt++];
				}
			}
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	* 四舍五入格式数字 
	* 
	* @param num   要格式化数字
	* @param count 小数点后面要保留的位数
	* @return      格式化数字字符 如果count<=0则默认为2位小数。
	*/
	public static String formatNum(double num, int count) {
		String size = "";
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				size = "0" + size;
			}
		} else {
			size = "00";
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("####0." + size);
		return df.format(num);

	}

	/**
	 * 对字符串进行URLEncoder.encode编码
	 * 
	 * @param str     要指定的字符串
	 * @param charset 指定要编码url的字符集
	 * @return        返回url指定编码集，如果为空则进行编码
	 */
	public static String UrlEncode(String str, String charset) {
		if (str == null)
			return "";
		String s = str;
		try {
			s = URLEncoder.encode(str, charset);
		} catch (Exception e) {

		}
		return s;
	}

	/**
	* 对字符串进行UrlDecode解码
	* 
	* @param str     要指定的字符串
	* @param charset 指定要解码url的字符集
	* @return        返回解码字符集
	*/
	public static String UrlDecode(String str, String charset) {
		if (str == null)
			return "";
		String s = str;
		try {
			s = URLDecoder.decode(str, charset);
		} catch (Exception e) {

		}
		return s;
	}

	/**
	 * 将字符串进行截取
	 * 
	 * @param  str    要截取字符串
	 * @param  length 要截断的长度 
	 * @param  suffix 添加后缀
	 * @return        返回截取后的替换的字符串 如果length<0,则等于suffix字符串
	 */
	public static String cutStr(String str, int length, String suffix) {
		if (str == null || suffix == null) {
			return null;
		}
		if (str.length() > length && length >= 0) {
			str = str.substring(0, length) + suffix;
		}
		return str;
	}

	private static SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat dateformatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取当前格式化的日期
	 * 
	 * @return 返回当前格式化日期
	 */
	public static String getCurrentDate() {
		return dateFormatDate.format(new java.util.Date());
	}

	/**
	 * 获取当前格式化的时间
	 * 
	 * @return 返回当前格式化时间
	 */
	public static String getCurrentTime() {
		return dateformatTime.format(new java.util.Date());
	}

	/**
	 * 判断字符串存在几个出现的字符
	 * 
	 * @param  str     传入的字符串
	 * @param  regstr  str字符串中出现的字符串
	 * @return regstr  返回的个数，如果str，regstr任意一个为空，则返回为0
	 */
	public static int countStr(String str, String regstr) {
		if ((str == null) || (str.equals("")) || (regstr == null) || regstr.equals("")) {
			return 0;
		}
		int count = 0;
		int pos = str.indexOf(regstr);
		while (pos != -1) {
			pos = str.indexOf(regstr, pos + regstr.length());
			count++;
		}
		return count;
	}

	/**
	 * 指定字符第一次出现的位置
	 * 
	 * @param  str    传入的字符串
	 * @param  regstr str字符串中出现的字符串
	 * @return regstr 返回的个数。如果str，regstr任意一个为空，则返回为0
	 */
	public static int Firstposition(String str, String regstr) {
		if ((str == null) || (str.equals("")) || (regstr == null) || regstr.equals("")) {
			return 0;
		}
		int pos = str.indexOf(regstr) + 1;
		return pos;
	}

	/**
	 * 去除字符串的标点符号和非法字符
	 * 
	 * @param str 传入的字符串
	 * @return    已经过滤的非法字符串，如果为null 返回空字符串""
	 */
	public static String extractChars(String str) {

		if (str == null || str.equals("")) {
			return "";
		}
		char[] c = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			if (Validator.isChar(c[i])) {
				sb.append(c[i]);
			} else {
				sb.append(" ");
			}
		}
		String formatStr = filterSpace(sb.toString()).trim();
		return formatStr;
	}

	/**
	 * 筛选出字符串中的数字
	 * 
	 * @param str 传入的字符串
	 * @return    数字类型的字符串
	 */
	public static String extractDigits(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		char[] c = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			if (Validator.isDigit(c[i])) {
				sb.append(c[i]);
			} else {
				sb.append(" ");
			}
		}

		String formatStr = filterSpace(sb.toString()).trim();
		return formatStr;
	}

	/**
	 * 过滤多个空格，只保留一个空格
	 * 
	 * @param  str 传入的字符串
	 * @return     已经去掉多个空格的字符串
	 */
	public static String filterSpace(String str) {
		Pattern p = Pattern.compile("\\s* |\t |\r |\n");
		Matcher m = p.matcher(str);
		str = m.replaceAll(" ").trim();
		return str;
	}

	/**
	 * 格式化字符串,替换单个char类型的字符
	 * 
	 * @param  str    需要格式化的字符串
	 * @param  oldSub 字符串中含有的单个字符
	 * @param  newSub 要转换后的字符
	 * @return        返回一个格式化的字符串
	 */
	public static String replaceStr(String str, char oldSub, char newSub) {
		return replaceStr(str, oldSub, new Character(newSub).toString());
	}

	/**
	 * 格式化字符串,替换单个char类型变成多个字符的串
	 * 
	 * @param  str    需要格式化的字符串
	 * @param  oldSub 字符串中含有的单个字符
	 * @param  newSub 要转换后的字符串
	 * @return        返回一个格式化的字符串
	 */
	public static String replaceStr(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}
		char[] c = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == oldSub) {
				sb.append(newSub);
			} else {
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 格式化字符串
	 * 
	 * @param str    需要格式化的字符串
	 * @param oldSub 字符串中含有字符串
	 * @param newSub 要转换后的字符串
	 * @return       返回一个格式化的字符串
	 */
	public static String replaceStr(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}
		int y = s.indexOf(oldSub);
		if (y >= 0) {
			StringBuffer sb = new StringBuffer();
			int length = oldSub.length();
			int x = 0;
			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);
				x = y + length;
				y = s.indexOf(oldSub, x);
			}
			sb.append(s.substring(x));
			return sb.toString();
		} else {
			return s;
		}
	}

	/**
	 * 格式化字符串 按照数组进行格式化
	 * 
	 * @param  str    需要格式化的字符串
	 * @param  oldSub 字符串中含有字符串
	 * @param  newSub 要转换后的字符串
	 * @return        返回一个格式化的字符串
	 */
	public static String replaceStr(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}
		if (oldSubs.length != newSubs.length) {
			return s;
		}
		for (int i = 0; i < oldSubs.length; i++) {
			s = replaceStr(s, oldSubs[i], newSubs[i]);
		}
		return s;
	}

	/**
	 * 拆分字符串
	 * @param  str   传入的字符串
	 * @param  split 分隔符
	 * @return       返回一个字符串数组
	 */
	public static String[] Stringsplit(String str, String split) {
		return split(str, split);
	}

	/**
	 * 拆分字符串，组装数组
	 * 
	 * @param  str       需要组装的字符串
	 * @param  delimiter 分隔符
	 * @return           字符串的数组
	 */
	public static String[] split(String str, String delimiter) {
		if (str == null || delimiter == null) {
			return new String[0];
		}
		str = str.trim();

		if (!str.endsWith(delimiter)) {
			str += delimiter;
		}
		if (str.equals(delimiter)) {
			return new String[0];
		}
		List nodeValues = new ArrayList();
		if (delimiter.equals("\n") || delimiter.equals("\r")) {
			try {
				BufferedReader br = new BufferedReader(new StringReader(str));

				String line = null;

				while ((line = br.readLine()) != null) {
					nodeValues.add(line);
				}
				br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} else {
			int offset = 0;
			int pos = str.indexOf(delimiter, offset);
			while (pos != -1) {
				nodeValues.add(str.substring(offset, pos));
				offset = pos + delimiter.length();
				pos = str.indexOf(delimiter, offset);
			}
		}
		return (String[]) nodeValues.toArray(new String[0]);
	}

	/**
	 * 所有字符大写
	 * 
	 * @param  str 传入的字符串
	 * @return     返回所有字符串大写
	 */
	public static String upperCase(String str) {
		if (str == null) {
			return null;
		} else {
			return str.toUpperCase();
		}
	}

	/**
	 * 所有字符小写
	 * 
	 * @param  str 传入的字符串
	 * @return     返回所有字符串小写
	 */
	public static String lowerCase(String str) {
		if (str == null) {
			return null;
		} else {
			return str.toLowerCase();
		}
	}

	/**
	 * 随即生成最大值以内的随机数
	 * 
	 * @param  maxNum 设定的随即最大数值
	 * @return        返回随即最大限制以内的随机值
	 */
	public static String getRandomNumber(int maxNum) {
		int intRd = 0; //存放随机数
		Random rdm = new Random();
		for (int i = 0; i < maxNum; i++)
			intRd = Math.abs(rdm.nextInt()) % maxNum + 1;
		String num = "";
		num = String.valueOf(intRd);
		return num;
	}

	/**
	 * 生成字母与数字组成的字符串
	 * 
	 * @return 生成字母与数字组成的字符串
	 */
	public static String getRandomStr() {
		String bbt = "ab0cde2fgh8ig6klm9no3pqr7stu4vws5yz1";
		String str = "";
		String restr = "";
		int intRd = 0; //存放随机数
		boolean[] bool = new boolean[36];
		Random rdm = new Random();
		for (int i = 0; i < 35; i++) {//随机排序
			do {
				intRd = rdm.nextInt(36); //假如产生的数相同继续循环
			} while (bool[intRd]);
			bool[intRd] = true;
			str += bbt.charAt(intRd);
		}
		intRd = 0;
		for (int i = 0; i < 20; i++) {//二次随机生成字符串
			intRd = Math.abs(rdm.nextInt()) % 34 + 1;
			restr += str.charAt(intRd);
		}
		return restr;
	}

	/**
	 * 日期字符串
	 * 
	 * @return 日期字符串
	 */
	public static String getDateString() {

		DateFormat d = DateFormat.getDateTimeInstance();
		Date now = new Date();
		String stt = d.format(now);
		String dates[] = stt.split(" ");
		String year[] = dates[0].split("-");
		String times[] = dates[1].split(":");
		String month = "";
		month = month + year[0];
		month = month + year[1];
		month = month + year[2];
		month = month + times[0];
		month = month + times[1];
		month = month + times[2];
		return month;
	}

	/**
	 * 密码进行MD5加密
	 * 
	 * @param password 需要加密的字符串
	 * @return         如果需要加密的字符串有效，则返回按照MD5加密后的字符串；<br>
	 *                 反之，返回null。
	 */
	public static String getMD5(String password) {
		if (password == null || "".equals(password))
			return null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = (int) b[i];
				v = v < 0 ? 0x100 + v : v;
				String hexString = Integer.toHexString(v);
				if (hexString.length() == 1)
					sb.append('0');
				sb.append(hexString);
			}
			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 返回下1个版本号，其中1.9的下1个版本是2.0。
	 * 
	 * @param currentVerString 当前版本号，如果为null那么返回1.0；<br>
	 *                         如果版本号为字符串，则返回null。
	 * @return                 返回下1个版本号。
	 */
	public static String getNextVer(String currentVerString) {
		if (Validator.isNull(currentVerString))
			return "1.0";
		try {
			float currentVer = Float.parseFloat(currentVerString);
			float nextVer = ((currentVer * 10) + 1) / 10;
			return nextVer + "";
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**  
	 * 去掉字符串里面的html代码。 
	 *   
	 * @param content 内容  
	 * @return        去掉后的内容  
	 */
	public static String stripHtml(String content) {
		// 换行换为空串   
		content = content.replaceAll("\r\n", "");
		// 去掉<>之间的东西   
		content = content.replaceAll("\\<.*?>", "");
		return content;
	}
	
	/**
	 * 将字符串中的URL，加上链接
	 * @param content
	 * @param regex
	 * @return
	 */
	public static String urlRegexReplace(String content,String regex){
		if(regex==null) regex = "[http|https|www]+[://]?+[0-9A-Za-z:/[-]_#[?][=][.]]*";
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		StringBuffer sbf = new StringBuffer();
		while(matcher.find()){
			String sub = matcher.group();
			String regexW = "[www]+[.]+[0-9A-Za-z:/[-]_#[?][=][.]]*";
			Pattern patternW = Pattern.compile(regexW,Pattern.CASE_INSENSITIVE);
			Matcher matcherW = patternW.matcher(sub);
			if(matcherW.matches()){
				matcher.appendReplacement(sbf,"<a href='http://"+sub+"'>"+sub+"</a>");
			}else{
				matcher.appendReplacement(sbf,"<a href='"+sub+"'>"+sub+"</a>");
			}
			sbf.append(" ");
		}
		matcher.appendTail(sbf);
		return sbf.toString();
	}
}
