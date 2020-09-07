package com.famiao.search.service;

import java.io.File;

import cn.hutool.core.io.FileUtil;

/**
 * @desc
 * @author famiao:曾斌
 * @version 创建时间：Jul 12, 2019 5:01:55 PM
 */
public class Main {

	public static void main(String[] args) {
	    File file = new File("D:\\Env\\project\\fm-search\\src\\main\\resources\\template\\Settings.json");
	    String json = FileUtil.readString(file, "UTF-8");
	    System.out.println(json);
	}

}
