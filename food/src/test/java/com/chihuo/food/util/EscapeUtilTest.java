package com.chihuo.food.util;

import org.apache.commons.text.StringEscapeUtils;

public class EscapeUtilTest {

	public static void main(String[] args) {
		System.out.println(StringEscapeUtils.escapeJava("中国"));
		System.out.println(StringEscapeUtils.unescapeJava("\\u4E2D\\u56FD"));
	}

}
