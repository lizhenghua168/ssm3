package com.worlk.mapper;

import com.worlk.entity.Company;
import org.springframework.stereotype.Repository;

@Repository(value = "companyMapper")
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer companyid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer companyid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}