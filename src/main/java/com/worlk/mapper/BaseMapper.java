package com.worlk.mapper;

import java.util.List;

/**
 * Created by XSF on 14-5-30.
 */
public interface BaseMapper <M extends java.io.Serializable, PK extends java.io.Serializable> {
    public PK save(M model);

    public void saveOrUpdate(M model);

    public void update(M model);

    public void merge(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);

    public int countAll();

    public List<M> listAll();

    public List<M> listAll(int pn, int pageSize);
}
