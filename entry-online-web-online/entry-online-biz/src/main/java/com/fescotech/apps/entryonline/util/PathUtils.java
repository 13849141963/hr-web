package com.fescotech.apps.entryonline.util;

public class PathUtils {
	/**
	 * 获取文件后缀名
	 * 
	 * @return
	 */
	public static String getFileExt(String fileName) {
		int iIndex = fileName.trim().lastIndexOf(".");
		if (iIndex < 0)
			return "";
		return fileName.substring(iIndex + 1).toLowerCase();
	}

	/**
	 * 获取文件名包括扩展名
	 */
	public static String getFileName(String fileName) {
		int iIndex = fileName.trim().lastIndexOf("\\");
		if (iIndex < 0) {
			return "";
		}
		return fileName.substring(iIndex + 1);
	}

	/**
	 * 获取文件名不包括扩展名
	 */
	public static String getFileNameWithoutExt(String fileName) {
		int iIndex = fileName.trim().lastIndexOf("\\");
		int jIndex = fileName.trim().lastIndexOf(".");
		if (iIndex < 0 && jIndex < 0) {
			return "";
		}
		if (iIndex < 0 && jIndex >= 0) {
			return fileName.substring(0, jIndex);
		}
		return fileName.substring(iIndex + 1, jIndex);
	}
}
