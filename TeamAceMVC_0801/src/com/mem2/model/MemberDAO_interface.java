package com.mem2.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO MemberVO);
//    public void update(MemberVO MemberVO);
//    public void delete(Integer Member_NO);
    public MemberVO findByPrimaryKey(Integer Member_NO);
    public MemberVO findID(String Member_ID);
    public List<MemberVO> getAll();
}
