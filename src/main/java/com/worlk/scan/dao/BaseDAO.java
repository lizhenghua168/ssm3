package com.worlk.scan.dao;

import java.util.List;

/**
 * Created by XSF on 14-3-24.
 */
public interface BaseDAO<E> {

    List<E> queryPage(final String statement, Object parameter, int pageNum, int pageSize);

}
