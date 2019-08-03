package com.authority.model;

import java.util.List;

public interface AuthorityDAO_interface {
    public AuthorityVO findByPrimaryKey(String features_no);
    public List<AuthorityVO> getAll();
}
