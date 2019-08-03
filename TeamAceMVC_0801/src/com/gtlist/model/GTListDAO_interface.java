package com.gtlist.model;

import java.util.List;

public interface GTListDAO_interface {
	public void insert(GTListVO gtListVO);
    public void update(GTListVO gtListVO);
    public void delete(String guideTour_no, String landscape_no);
    public GTListVO findByPrimaryKey(String guideTour_no, String landscape_no);
    public List<GTListVO> findByGuideTourNo(String guideTour_no);
    public List<GTListVO> getAll();
}
