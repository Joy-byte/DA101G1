/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.order_detail.controller;

import java.util.*;


public class jdbcUtil_CompositeQuery_Order_detail {
	
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("order_amosum_start".equals(columnName)) // 用於起始金額
			aCondition = "order_amosum" + ">=" + value;
		else if ("order_amosum_end".equals(columnName)) // 用於低於金額
			aCondition = "order_amosum" + "<=" + value;
		else if ("order_time_start".equals(columnName))            // 用於Oracle的date開始時間
			aCondition = "to_char(" + "order_time" + ",'yyyy-mm-dd')>='" + value + "'";
		else if ("order_time_end".equals(columnName))			  // 用於Oracle的date結束時間
			aCondition = "to_char(" + "order_time" + ",'yyyy-mm-dd')<='" + value + "'";
		else if ("mem_no".equals(columnName) || "order_status".equals(columnName) )			  // 用於Oracle的date
			aCondition = columnName + " like '%" + value + "%'";
		//會員編號/廠商編號/訂單狀態

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("mem_no", new String[] { "MB00001" });
		map.put("order_status", new String[] { "O2" });
		map.put("order_amosum_start", new String[] { "0" });
		map.put("order_amosum_end", new String[] { "600" });
		map.put("order_time_start", new String[] { "2019-07-07" });
		map.put("order_time_end", new String[] { "2019-07-10" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from order_detail "
				          + jdbcUtil_CompositeQuery_Order_detail.get_WhereCondition(map)
				          + "order by order_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}

}
