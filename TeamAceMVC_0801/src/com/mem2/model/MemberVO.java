package com.mem2.model;

import java.io.InputStream;

public class MemberVO {
	private Integer Member_NO;
	private String Member_NAME;
	private String Member_ID;
	private String Member_PSW;
//	private InputStream Member_PIC;
	private byte[] Member_PIC;
	
	public Integer getMember_NO() {
		return Member_NO;
	}
	public void setMember_NO(Integer member_NO) {
		Member_NO = member_NO;
	}
	public String getMember_NAME() {
		return Member_NAME;
	}
	public void setMember_NAME(String member_NAME) {
		Member_NAME = member_NAME;
	}
	public String getMember_ID() {
		return Member_ID;
	}
	public void setMember_ID(String member_ID) {
		Member_ID = member_ID;
	}
	public String getMember_PSW() {
		return Member_PSW;
	}
	public void setMember_PSW(String member_PSW) {
		Member_PSW = member_PSW;
	}
	public byte[] getMember_PIC() {
		return Member_PIC;
	}
	public void setMember_PIC(byte[] member_PIC) {
		Member_PIC = member_PIC;
	}
	
	
	
	
	
	
	
	
}
