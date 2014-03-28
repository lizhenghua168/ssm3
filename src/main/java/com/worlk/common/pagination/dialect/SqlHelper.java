package com.worlk.common.pagination.dialect;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Date Created  2014-2-25
 *
 * @author
 * @version 1.0
 */
public abstract class SqlHelper {
    private static Logger logger = LoggerFactory.getLogger(SqlHelper.class);

    /**
     * �����ݿ����ѯ�ܵļ�¼����������ҳ������д����ҳ����<code>PageParameter</code>,���������߾Ϳ���ͨ�� ��ҳ����
     * <code>PageParameter</code>��������Ϣ��
     *
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     */
    public static int getTotalCount(String sql, Connection connection, MappedStatement mappedStatement,
                              BoundSql boundSql) {
        // ��¼�ܼ�¼��
        String countSql = "select count(0) from (" + sql + ") as total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        int totalCount = 0;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
            try {
                countStmt.close();
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
            return totalCount;
        }

    }

    /**
     * ��SQL����(?)��ֵ
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }
}
