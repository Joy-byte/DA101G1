package com.features.model;

import java.util.List;

public interface FeaturesDAO_interface {
	public void insert(FeaturesVO FeaturesVO);
    public void update(FeaturesVO FeaturesVO);
    public void delete(String features_no);
    public FeaturesVO findByPrimaryKey(String features_no);
    public List<FeaturesVO> getAll();
}
