package com.guidereport.model;

import java.util.List;

public interface GuideReportDAO_interface {
	public void insert(GuideReportVO guideReportVO);
    public void update(GuideReportVO guideReportVO);
    public void delete(String guideRep_no);
    public GuideReportVO findByPrimaryKey(String guideRep_no);
    public List<GuideReportVO> getAll();
}
