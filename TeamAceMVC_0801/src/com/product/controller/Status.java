package com.product.controller;

public class Status {
	public static String interpreter(String status) {
	
		String orderstatus =null;
		
		switch (status) {
		case "O1":
			orderstatus = "檢貨中";
			break;

		case "O2":
			orderstatus = "已出貨";
			break;
			
		case "O3":
			orderstatus = "已取消";
			break;
			
		case "O4":
			orderstatus = "退貨";
			break;

		}

		return orderstatus;
	}

}
