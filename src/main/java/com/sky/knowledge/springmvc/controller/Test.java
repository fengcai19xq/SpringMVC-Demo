package com.sky.knowledge.springmvc.controller;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "zhaogang.sunyabing,zhaogang.xuqian" ;
		String [] str2 = str.split(",");
		System.out.println(str2[0]);
		List list = null ;
		System.out.println(list.isEmpty());
	}

}
