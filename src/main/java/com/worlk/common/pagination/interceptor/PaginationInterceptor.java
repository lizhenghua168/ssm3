package com.worlk.common.pagination.interceptor;

import com.worlk.common.pagination.dialect.DatabaseType;
import com.worlk.common.pagination.dialect.Dialect;
import com.worlk.common.pagination.dialect.DialectFactory;
import com.worlk.common.pagination.dialect.SqlHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by XSF on 14-3-24.
 */
@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class})})
public class PaginationInterceptor implements Interceptor
{
    private static final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final String DEFAULT_DIALECT = "mysql"; // ���ݿ�����(Ĭ��Ϊmysql)
    private static final String DEFAULT_PAGESQLREGEX = ".*Page$"; // ��Ҫ���ص�ID(����ƥ��)
    private static Dialect dialect = null; // ���ݿ�����(Ĭ��Ϊmysql)
    private static String pageSqlRegex = ""; // ��Ҫ���ص��ַ���(����ƥ��)
    private static final ThreadLocal<Integer> PAGINATION_TOTAL = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,
                DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        if(rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        MappedStatement mappedStatement = (MappedStatement)
                metaStatementHandler.getValue("delegate.mappedStatement");
        // ֻ��д��Ҫ��ҳ��sql��䡣ͨ��MappedStatement��IDƥ�䣬Ĭ����д��Page��β��
        //  MappedStatement��sql
        if (mappedStatement.getId().matches(pageSqlRegex)) {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            String sql = boundSql.getSql();
            // ��дsql
            String pageSql = dialect.getPageSql(sql, rowBounds.getOffset(),
                    rowBounds.getLimit());
            logger.info("sql: {}", sql.trim());
            logger.info("limit sql: {}", pageSql);
            metaStatementHandler.setValue("delegate.boundSql.sql",pageSql);
            // ���������ҳ�󣬾Ͳ���Ҫmybatis���ڴ��ҳ�ˣ����������������������
            metaStatementHandler.setValue("delegate.rowBounds.offset",
                    RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit",
                    RowBounds.NO_ROW_LIMIT);
            Connection connection = (Connection) invocation.getArgs()[0];

            // ����ܼ�¼��
            int totalCount = SqlHelper.getTotalCount(sql, connection, mappedStatement, boundSql);

            PAGINATION_TOTAL.set(totalCount);
        }
        // ��ִ��Ȩ������һ��������
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // ��Ŀ������StatementHandler����ʱ���Ű�װĿ���࣬����ֱ�ӷ���Ŀ�걾��,����Ŀ�걻����Ĵ���
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties)
    {
        String dialectClass = properties.getProperty("dialectClass");
        if(StringUtils.isBlank(dialectClass)) {
            String dialectShortName = properties.getProperty("dialect");
            checkDialect(dialectShortName);
            dialect = DialectFactory.buildDialect(DatabaseType.valueOf(dialectShortName.toUpperCase()));
        } else {
            try {
                dialect = (Dialect) Class.forName(dialectClass).newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Plug-in [PaginationInterceptor] cannot create dialect instance by dialectClass: " + dialectClass);
            }
        }
        pageSqlRegex = properties.getProperty("stmtIdRegex",DEFAULT_PAGESQLREGEX);
    }

    private void checkDialect(String dialectShortName) {
        try {
            DatabaseType.valueOf(dialectShortName.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Plug-in [PaginationInterceptor] the dialect of the attribute value is invalid!");
        }
    }

    /**
     * Get Pagination total
     *
     * @return
     */
    public static int getPaginationTotal(){
        return PAGINATION_TOTAL.get();
    }

    public static void clean(){
        PAGINATION_TOTAL.remove();
    }

    public static Dialect getDialect() {
        return dialect;
    }

    public static void setDialect(Dialect dialect) {
        PaginationInterceptor.dialect = dialect;
    }

    public static String getPageSqlRegex() {
        return pageSqlRegex;
    }

    public static void setPageSqlRegex(String pageSqlRegex) {
        PaginationInterceptor.pageSqlRegex = pageSqlRegex;
    }
}
