package com.gtclist.model;

import java.util.List;

public interface GTCListDAO_interface {
	public void insert(GTCListVO gtcListVO);
    public void delete(String guideTour_no, String category_no);
    public List<GTCListVO> getCategoryByGTno(String guideTour_no);
    public List<GTCListVO> getGuideTourByCno(String category_no);
    public List<GTCListVO> getAll();
}
