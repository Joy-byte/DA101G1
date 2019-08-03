package com.resorder.model;

import java.util.List;

public interface ResOrderDAO_interface {
	public void insert(ResOrderVO resOrderVO);
    public void update(ResOrderVO resOrderVO);
    public void delete(String resOrder_no);
    public ResOrderVO findByPrimaryKey(String resOrder_no);
    public List<ResOrderVO> getAll();
}
