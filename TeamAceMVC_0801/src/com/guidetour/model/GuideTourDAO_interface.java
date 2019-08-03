package com.guidetour.model;

import java.util.List;

public interface GuideTourDAO_interface {
	public String insert(GuideTourVO guideTourVO);
    public void update(GuideTourVO guideTourVO);
    public void delete(String guideTour_no);
    public GuideTourVO findByPrimaryKey(String guideTour_no);
    public List<GuideTourVO> getAll();
}
