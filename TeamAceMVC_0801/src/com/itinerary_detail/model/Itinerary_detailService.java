package com.itinerary_detail.model;

import java.util.List;

public class Itinerary_detailService {

	private Itinerary_detailDAO_interface dao;

	public Itinerary_detailService() {
		dao = new Itinerary_detailDAO();
	}

	public Itinerary_detailVO addItd(String itinerary_no, String[] landscape_no, String itinerary_content) {

		Itinerary_detailVO itinerary_detailVO = new Itinerary_detailVO();
		for(int i =0;i<landscape_no.length;i++) {			
			itinerary_detailVO.setItinerary_no(itinerary_no);
			itinerary_detailVO.setLandscape_no(landscape_no[i]);
			itinerary_detailVO.setItinerary_content(itinerary_content);
			dao.insert(itinerary_detailVO);
		}
		return itinerary_detailVO;  //目前沒用,先回傳最後一筆
	}

	public void addEmp(Itinerary_detailVO itinerary_detailVO) {
		dao.insert(itinerary_detailVO);
	}
	
//	public Itinerary_detailVO updateEmp(Integer empno, String ename, String job,
//			java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {
//
//		Itinerary_detailVO empVO = new Itinerary_detailVO();
//
//		empVO.setEmpno(empno);
//		empVO.setEname(ename);
//		empVO.setJob(job);
//		empVO.setHiredate(hiredate);
//		empVO.setSal(sal);
//		empVO.setComm(comm);
//		empVO.setDeptno(deptno);
//		dao.update(empVO);
//
//		return dao.findByPrimaryKey(empno);
//	}
	
//	public void updateEmp(Itinerary_detailVO empVO) {
//		dao.update(empVO);
//	}

	public List<Itinerary_detailVO> getAll() {
		return dao.getAll();
	}
	public List<Itinerary_detailVO> getLands(String itinerary_no) {
		return dao.getLas(itinerary_no);
	}
	public Itinerary_detailVO getOneItinerary_detail(String itinerary_no) {
		return dao.findByPrimaryKey(itinerary_no);
	}
}
