package com.guide.model;

import java.util.List;

public interface GuideDAO_interface {
	public void insert(GuideVO guideVO);
    public void update(GuideVO guideVO);
    public void updateGuideStatus(String guide_no, String guide_status);
    public void delete(String guide_no);
    public GuideVO findByPrimaryKey(String guide_no);
    public GuideVO findByMemNo(String mem_no);
    public List<GuideVO> getAll();
}
