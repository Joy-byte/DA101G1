package com.mem.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO MemberVO);
    public void update(MemberVO MemberVO);
    public void update2(MemberVO MemberVO);
    public void delete(String mem_no);
    public MemberVO findByPrimaryKey(String mem_no);
    public MemberVO findMem_email(String mem_email);
    public List<MemberVO> getAll();
    
  //查詢一個會員點數
    public MemberVO findMenAmoByMenNo(String mem_no);
    
    //修改會員點數
    public void updateAboutMenAmo(MemberVO MemberVO);
}
