package com.worlk.scan.dao.impl;

import com.worlk.scan.dao.BaseDAO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XSF on 14-3-24.
 */
@Repository("baseDAO")
public class BaseDAOImpl<E> extends SqlSessionDaoSupport implements BaseDAO {

    @Override
    public List<E> queryPage(String statement, Object parameter,
                             int offset, int limit) {
        /*RowBounds rowBounds = new RowBounds(offset, limit);

        return getSqlSession().selectList(statement, parameter, rowBounds);*/
        return null;
    }

}
