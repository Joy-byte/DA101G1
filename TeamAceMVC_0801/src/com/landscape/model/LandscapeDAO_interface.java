package com.landscape.model;

import java.util.List;
import java.util.Set;

public interface LandscapeDAO_interface {
    public void insert(LandscapeVO landscapeVO);
    public void update(LandscapeVO landscapeVO);
    public void delete(String landscape_no);
    public LandscapeVO findByPrimaryKey(String landscape_no);
    public List<LandscapeVO> getAll();
	public Set<String> getAllCity();

}
