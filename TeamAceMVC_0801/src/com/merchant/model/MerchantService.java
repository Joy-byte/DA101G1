package com.merchant.model;

import java.util.List;


public class MerchantService {
	private MerchantDAO_interface dao;

	public MerchantService() {
		dao = new MerchantDAO();
	}

	public MerchantVO addMerchant(String merchant_id, String merchant_pass, String merchant_name,
			String merchant_pm, String merchant_add, String merchant_tel, String merchant_email,
			String merchant_status, String merchant_ps, byte[] merchant_img) {

		MerchantVO merchantVO = new MerchantVO();

		merchantVO.setMerchant_id(merchant_id);
		merchantVO.setMerchant_pass(merchant_pass);
		merchantVO.setMerchant_name(merchant_name);
		merchantVO.setMerchant_pm(merchant_pm);
		merchantVO.setMerchant_add(merchant_add);
		merchantVO.setMerchant_tel(merchant_tel);
		merchantVO.setMerchant_email(merchant_email);
		merchantVO.setMerchant_status(merchant_status);
		merchantVO.setMerchant_ps(merchant_ps);
		merchantVO.setMerchant_img(merchant_img);
		dao.insert(merchantVO);

		return merchantVO;
	}

	public MerchantVO updateMerchant(String merchant_no, String merchant_id, String merchant_pass, String merchant_name,
			String merchant_pm, String merchant_add, String merchant_tel, String merchant_email,
			String merchant_status, String merchant_ps, byte[] merchant_img) {

		MerchantVO merchantVO = new MerchantVO();

		merchantVO.setMerchant_no(merchant_no);
		merchantVO.setMerchant_id(merchant_id);
		merchantVO.setMerchant_pass(merchant_pass);
		merchantVO.setMerchant_name(merchant_name);
		merchantVO.setMerchant_pm(merchant_pm);
		merchantVO.setMerchant_add(merchant_add);
		merchantVO.setMerchant_tel(merchant_tel);
		merchantVO.setMerchant_email(merchant_email);
		merchantVO.setMerchant_status(merchant_status);
		merchantVO.setMerchant_ps(merchant_ps);
		merchantVO.setMerchant_img(merchant_img);
		dao.update(merchantVO);

		return merchantVO;
	}

	public void deleteMerchant(String merchant_no) {
		dao.delete(merchant_no);
	}

	public MerchantVO getOneMerchant(String merchant_no) {
		return dao.findByPrimaryKey(merchant_no);
	}
	

	public List<MerchantVO> getAll() {
		return dao.getAll();
	}
	
	public MerchantVO getOneMerchantId(String merchant_id) {
		return dao.findByMerchantId(merchant_id);
	}
	
	public List<MerchantVO> getAllOneMerchantVO(String merchant_no) {
		return dao.getAllOneMerchantVO(merchant_no);
		
	}
	
	//更改單一廠商狀態
	public MerchantVO updateOneMerchantStatus(String merchant_no,String merchant_status) {

		MerchantVO merchantVO = new MerchantVO();

		merchantVO.setMerchant_no(merchant_no);
		merchantVO.setMerchant_status(merchant_status);

		dao.updateMerchantStatus(merchantVO);

		return merchantVO;
	}
	
	//查詢單一狀態有關廠商
	public List<MerchantVO> getOneStatusOfAll(String merchant_status) {
		return dao.getOneStatusOfAll(merchant_status);
	}
	
}
