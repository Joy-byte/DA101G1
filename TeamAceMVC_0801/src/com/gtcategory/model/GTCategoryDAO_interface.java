package com.gtcategory.model;

import java.util.List;


public interface GTCategoryDAO_interface {
	
        public void insert(GTCategoryVO gtCategoryVO);
        public void update(GTCategoryVO gtCategoryVO);
        public void delete(String gtCategory_no);
        public GTCategoryVO findByPrimaryKey(String gtCategory_no);
        public List<GTCategoryVO> getAll();

}
