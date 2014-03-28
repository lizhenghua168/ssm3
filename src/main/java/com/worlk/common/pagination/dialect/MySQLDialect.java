package com.worlk.common.pagination.dialect;

/**
 * Created by XSF on 14-3-24.
 */
public class MySQLDialect extends Dialect {
    /**
     * 数据库本身是否支持分页查询
     *
     * @return {@code true} 支持分页查询
     */
    @Override
    public boolean supportsLimit() {
        return true;
    }

    /**
     * 将sql包装成数据库支持的特有查询语句
     *
     * @param sql    SQL语句
     * @param offset 开始位置
     * @param limit  每页显示的记录数
     * @return 数据库专属分页查询sql
     */
    @Override
    public String getPageSql(String sql, int offset, int limit) {
        return getPageSql(sql, offset, String.valueOf(offset), String.valueOf(limit));
    }

    private String getPageSql(final String sql, final int offset,
                                  final String offsetPlaceholder, final String limitPlaceholder){
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append(" limit ");
        if(offset > 0){
            stringBuilder.append(offsetPlaceholder).append(",").append(limitPlaceholder);
        }else{
            stringBuilder.append(limitPlaceholder);
        }

        return stringBuilder.toString();
    }
}
