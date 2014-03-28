package com.worlk.common.pagination.dialect;

/**
 * Created by XSF on 14-3-24.
 */
public class MySQLDialect extends Dialect {
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
