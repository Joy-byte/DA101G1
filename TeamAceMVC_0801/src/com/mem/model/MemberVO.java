package com.mem.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable{
	private String mem_no;// 編號
	private String mem_email;// 電子郵件
	private String mem_psw;// 密碼
	private String mem_name;// 姓名
	private String mem_nickname;// 暱稱
	private Date mem_birthday;// 生日
	private String mem_adrs;// 地址
	private String mem_tel;// 電話
	private String mem_status;// 狀態
	private Integer mem_amo;// 會員點數
	private byte[] mem_pic;// 會員照片

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_psw() {
		return mem_psw;
	}

	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	public Date getMem_birthday() {
		return mem_birthday;
	}

	public void setMem_birthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}

	public String getMem_adrs() {
		return mem_adrs;
	}

	public void setMem_adrs(String mem_adrs) {
		this.mem_adrs = mem_adrs;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_status() {
		return mem_status;
	}

	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}

	public Integer getMem_amo() {
		return mem_amo;
	}

	public void setMem_amo(Integer mem_amo) {
		this.mem_amo = mem_amo;
	}

	public byte[] getMem_pic() {
		return mem_pic;
	}

	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}

}
