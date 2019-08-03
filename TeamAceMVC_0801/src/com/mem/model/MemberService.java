package com.mem.model;

import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String mem_email, String mem_psw, String mem_name, String mem_nickname,
			java.sql.Date mem_birthday, String mem_adrs, String mem_tel, String mem_status, byte[] mem_pic) {

		MemberVO memberVO = new MemberVO();

//		memberVO.setMem_no(mem_no);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_psw(mem_psw);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_adrs(mem_adrs);
		memberVO.setMem_tel(mem_tel);
		memberVO.setMem_status(mem_status);
//		memberVO.setMem_amo(mem_amo);
		memberVO.setMem_pic(mem_pic);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(String mem_no, String mem_email, String mem_psw, String mem_name, String mem_nickname,
			java.sql.Date mem_birthday, String mem_adrs, String mem_tel, String mem_status, Integer mem_amo, byte[] mem_pic) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_psw(mem_psw);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_adrs(mem_adrs);
		memberVO.setMem_tel(mem_tel);
		memberVO.setMem_status(mem_status);
		memberVO.setMem_amo(mem_amo);
		memberVO.setMem_pic(mem_pic);
		dao.update(memberVO);

		return memberVO;
	}

	public MemberVO updateMember2(String mem_no, String mem_email, String mem_psw, String mem_name, String mem_nickname,
			java.sql.Date mem_birthday, String mem_adrs, String mem_tel, String mem_status, Integer mem_amo) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_psw(mem_psw);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_adrs(mem_adrs);
		memberVO.setMem_tel(mem_tel);
		memberVO.setMem_status(mem_status);
		memberVO.setMem_amo(mem_amo);
		dao.update2(memberVO);
		return memberVO;
	}

	public void deleteMember(String mem_no) {
		dao.delete(mem_no);
	}

	public MemberVO getOneMember(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}

	public MemberVO getOneMem_email(String mem_email) {
		return dao.findMem_email(mem_email);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	
	//查詢單一會員點數
	public MemberVO findMenAmoByMenNo(String mem_no) {
		return dao.findMenAmoByMenNo(mem_no);
	}

	//更改會員點數
	public MemberVO updateAboutMenAmo(String mem_no, Integer mem_amo) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setMem_amo(mem_amo);
		dao.updateAboutMenAmo(memberVO);
		return memberVO;
	}
}
