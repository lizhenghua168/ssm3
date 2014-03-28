package com.worlk.common.pagination.dialect;

/**
 * Created by XSF on 14-3-24.
 */
public abstract class Dialect {
    /**
     * ���ݿⱾ���Ƿ�֧�ַ�ҳ��ѯ
     *
     * @return {@code true} ֧�ַ�ҳ��ѯ
     */
    public abstract boolean supportsLimit();

    /**
     * ��sql��װ�����ݿ�֧�ֵ����в�ѯ���
     *
     * @param sql SQL���
     * @param offset ��ʼλ��
     * @param limit ÿҳ��ʾ�ļ�¼��
     * @return ���ݿ�ר����ҳ��ѯsql
     */
    public abstract String getPageSql(String sql, int offset, int limit);

    /**
     * ��sql��װ��ͳ������SQL
     * @param sql SQL���
     * @return ͳ������SQL
     */
    public String getCountSql(String sql) {
        return "select count(1) from (" + sql + ") as total";
    }
}
