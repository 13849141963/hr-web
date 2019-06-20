package com.fescotech.apps.medicare.reimb.biz.util;

import org.junit.Test;

import com.fescotech.apps.entryonline.util.PathUtils;

public class PathUtilsTest {
	@Test
	public void test1() {
		String fileName = PathUtils.getFileNameWithoutExt("dfd.jpg");
		System.out.println(fileName);
	}
}
