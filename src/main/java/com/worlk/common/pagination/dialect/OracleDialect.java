package com.worlk.common.pagination.dialect;

/**
 * Created by XSF on 14-3-24.
 */
public class OracleDialect extends Dialect {
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

        StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
        if (offset >= 0) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (offset >= 0) {
//            String endString = offsetPlaceholder + "+" + limitPlaceholder;
            String endString = String.valueOf(Integer.parseInt(offsetPlaceholder) + Integer.parseInt(limitPlaceholder));
            pagingSelect.append(" ) row_ ) where rownum_ <= ")
                    .append(endString).append(" and rownum_ > ").append(offsetPlaceholder);
        } else {
            pagingSelect.append(" ) where rownum <= ").append(limitPlaceholder);
        }

        return pagingSelect.toString();
    }
}
