package com.wsj.eshop.util;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID() {
		//���ɵ���������С�-���ַ�����Ҫȥ��
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
