package com.deposit.model;

import java.util.List;


public class DepositService {
	private DepositDAO_interface dao;

	public DepositService() {
		dao = new DepositDAO();
	}

	public DepositVO addDeposit(String mem_no, java.sql.Date deposit_time, Integer  deposit_amo) {

		DepositVO depositVO = new DepositVO();

		depositVO.setMem_no(mem_no);
		depositVO.setDeposit_time(deposit_time);
		depositVO.setDeposit_amo(deposit_amo);

		dao.insert(depositVO);

		return depositVO;
	}

	public DepositVO updateDeposit(String  deposit_no , String  mem_no, java.sql.Date  deposit_time, Integer  deposit_amo) {

		DepositVO depositVO = new DepositVO();

		depositVO.setDeposit_no(deposit_no);
		depositVO.setMem_no(mem_no);
		depositVO.setDeposit_time(deposit_time);
		depositVO.setDeposit_amo(deposit_amo);
		dao.update(depositVO);

		return depositVO;
	}

	public void deleteDeposit(String deposit_no) {
		dao.delete(deposit_no);
	}

	public List<DepositVO> getDeposit(String mem_no) {
		return dao.findByMemNo(mem_no);
	}
	
	public List<DepositVO> getAll() {
		return dao.getAll();
	}
	
	public DepositVO getOneDeposit(String deposit_no) {
		return dao.findByPrimaryKey(deposit_no);
	}
	

}
