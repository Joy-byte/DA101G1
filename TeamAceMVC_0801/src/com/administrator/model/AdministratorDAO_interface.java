package com.administrator.model;

import java.util.List;

public interface AdministratorDAO_interface {
	public void insert(AdministratorVO AdministratorVO);
    public void update(AdministratorVO AdministratorVO);
    public void delete(String administrator_no);
    public AdministratorVO findByPrimaryKey(String administrator_no);
    public AdministratorVO findID(String administrator_id);
    public List<AdministratorVO> getAll();
}
