package com.administrator.model;

import java.util.List;

public class AdministratorService {
	
	private AdministratorDAO_interface dao;
	
	public AdministratorService() {
		dao = new AdministratorDAO();
	}
	
	public AdministratorVO addAdministrator(String administrator_id, String administrator_psw, String administrator_name) {
		
		AdministratorVO AdministratorVO = new AdministratorVO();
		
		AdministratorVO.setAdministrator_id(administrator_id);
		AdministratorVO.setAdministrator_psw(administrator_psw);
		AdministratorVO.setAdministrator_name(administrator_name);
		dao.insert(AdministratorVO);
		
		return AdministratorVO;
	}
	
	public AdministratorVO updateAdministrator(String administrator_no, String administrator_id, String administrator_psw, String administrator_name) {
	
		AdministratorVO AdministratorVO = new AdministratorVO();
		
		AdministratorVO.setAdministrator_no(administrator_no);
		AdministratorVO.setAdministrator_id(administrator_id);
		AdministratorVO.setAdministrator_psw(administrator_psw);
		AdministratorVO.setAdministrator_name(administrator_name);
		dao.update(AdministratorVO);
		
		return AdministratorVO;
	}
	
	public void deleteAdministrator(String administrator_no) {
		dao.delete(administrator_no);
	}
	
	public AdministratorVO getOneAdministrator(String administrator_no) {
		return dao.findByPrimaryKey(administrator_no);
	}
	
	public List<AdministratorVO> getAll(){
		return dao.getAll();
	}
}
