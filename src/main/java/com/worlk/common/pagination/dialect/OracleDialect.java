package com.worlk.common.pagination.dialect;

/**
 * Created by XSF on 14-3-24.
 */
public class OracleDialect extends Dialect {
    /**
     * ���ݿⱾ���Ƿ�֧�ַ�ҳ��ѯ
     *
     * @return {@code true} ֧�ַ�ҳ��ѯ
     */
    @Override
    public boolean supportsLimit() {
        return true;
    }

    /**
     * ��sql��װ�����ݿ�֧�ֵ����в�ѯ���
     *
     * @param sql    SQL���
     * @param offset ��ʼλ��
     * @param limit  ÿҳ��ʾ�ļ�¼��
     * @return ���ݿ�ר����ҳ��ѯsql
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
