package com.worlk.dao;

import com.worlk.entity.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer companyid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer companyid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}