package com.wish.list.model;

import java.util.List;

public interface Wish_listDAO_interface {
    public void insert(Wish_listVO wish_listVO);
    public void delete(String mem_no, String product_no);
    public List<Wish_listVO> findByMemberNo(String mem_no);
    public List<Wish_listVO> findByPrimaryKey(String mem_no, String product_no);
    public List<Wish_listVO> getAll();
}
