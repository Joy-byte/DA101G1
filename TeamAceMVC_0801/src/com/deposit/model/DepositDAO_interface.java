package com.deposit.model;

import java.util.*;

public interface DepositDAO_interface {
    public void insert(DepositVO depositVO);
    public void update(DepositVO depositVO);
    public void delete(String deposit_no);
    public DepositVO findByPrimaryKey(String deposit_no);
    
    public DepositVO findByPrimaryKey(String deposit_no, String mem_no);
    public List<DepositVO> findByMemNo(String mem_no);
    public List<DepositVO> getAll();
 
}
