package com.deposit.model;

import java.sql.Date;

public class DepositVO {
	private String   deposit_no;
	private String   mem_no;
	private Date     deposit_time;
	private Integer  deposit_amo;
	
	public String getDeposit_no() {
		return deposit_no;
	}
	public void setDeposit_no(String deposit_no) {
		this.deposit_no = deposit_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Date getDeposit_time() {
		return deposit_time;
	}
	public void setDeposit_time(Date deposit_time) {
		this.deposit_time = deposit_time;
	}
	public Integer getDeposit_amo() {
		return deposit_amo;
	}
	public void setDeposit_amo(Integer deposit_amo) {
		this.deposit_amo = deposit_amo;
	}
	
	

}
