package com.wsj.eshop.util;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID() {
		//生成的随机数含有“-”字符，需要去掉
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
