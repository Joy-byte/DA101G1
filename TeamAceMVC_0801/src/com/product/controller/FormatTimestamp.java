package com.product.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FormatTimestamp {
	
	
  public static String  getFormatTS(Timestamp ts) {
        
	  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	  String str = sf.format(ts);
	  
	  return str;
  }
  
  public static String  getFormatTST(Timestamp ts) {
      
	  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String str = sf.format(ts);
	  
	  return str;
  }
  

}
