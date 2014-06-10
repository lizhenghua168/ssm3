package com.worlk.common.pagination.dialect;

import com.worlk.common.pagination.domain.Order;

import java.util.List;

/**
 * Created by XSF on 14-3-24.
 */
public class Dialect {
    /**
     * ���ݿⱾ���Ƿ�֧�ַ�ҳ��ѯ
     *
     * @return {@code true} ֧�ַ�ҳ��ѯ
     */
    public boolean supportsLimit(){
        return false;
    }

    public boolean supportsLimitOffset() {
        return supportsLimit();
    }

    /**
     * ��sql��װ�����ݿ�֧�ֵ����в�ѯ���
     *
     * @param sql SQL���
     * @param offset ��ʼλ��
     * @param limit ÿҳ��ʾ�ļ�¼��
     * @return ���ݿ�ר����ҳ��ѯsql
     */
    public String getPageSql(String sql, int offset, int limit){
        throw new UnsupportedOperationException("paged queries not supported");
    }

    /**
     * ��sql��װ��ͳ������SQL
     * @param sql SQL���
     * @return ͳ������SQL
     */
    public String getCountSql(String sql) {
        return "select count(1) from (" + sql + ") as total";
    }

    /**
     * ��sqlת��Ϊ�������SQL
     * @param sql SQL���
     * @return �ܼ�¼����sql
     */
    public String getSortString(String sql, List<Order> orders){
        if(orders == null || orders.isEmpty()){
            return sql;
        }

        StringBuffer buffer = new StringBuffer("select * from (").append(sql).append(") temp_order order by ");
        for(Order order : orders){
            if(order != null){
                buffer.append(order.toString())
                        .append(", ");
            }

        }
        buffer.delete(buffer.length()-2, buffer.length());
        return buffer.toString();
    }
}
