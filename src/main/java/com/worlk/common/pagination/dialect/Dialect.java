package com.worlk.common.pagination.dialect;

import com.worlk.common.pagination.domain.Order;

import java.util.List;

/**
 * Created by XSF on 14-3-24.
 */
public class Dialect {
    /**
     * 数据库本身是否支持分页查询
     *
     * @return {@code true} 支持分页查询
     */
    public boolean supportsLimit(){
        return false;
    }

    public boolean supportsLimitOffset() {
        return supportsLimit();
    }

    /**
     * 将sql包装成数据库支持的特有查询语句
     *
     * @param sql SQL语句
     * @param offset 开始位置
     * @param limit 每页显示的记录数
     * @return 数据库专属分页查询sql
     */
    public String getPageSql(String sql, int offset, int limit){
        throw new UnsupportedOperationException("paged queries not supported");
    }

    /**
     * 将sql包装成统计总数SQL
     * @param sql SQL语句
     * @return 统计总数SQL
     */
    public String getCountSql(String sql) {
        return "select count(1) from (" + sql + ") as total";
    }

    /**
     * 将sql转换为带排序的SQL
     * @param sql SQL语句
     * @return 总记录数的sql
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
