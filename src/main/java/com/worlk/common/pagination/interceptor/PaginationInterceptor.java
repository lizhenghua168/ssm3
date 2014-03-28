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
    private static final String DEFAULT_DIALECT = "mysql"; // 数据库类型(默认为mysql)
    private static final String DEFAULT_PAGESQLREGEX = ".*Page$"; // 需要拦截的ID(正则匹配)
    private static Dialect dialect = null; // 数据库类型(默认为mysql)
    private static String pageSqlRegex = ""; // 需要拦截的字符串(正则匹配)
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
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的
        //  MappedStatement的sql
        if (mappedStatement.getId().matches(pageSqlRegex)) {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            String sql = boundSql.getSql();
            // 重写sql
            String pageSql = dialect.getPageSql(sql, rowBounds.getOffset(),
                    rowBounds.getLimit());
            logger.info("sql: {}", sql.trim());
            logger.info("limit sql: {}", pageSql);
            metaStatementHandler.setValue("delegate.boundSql.sql",pageSql);
            // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
            metaStatementHandler.setValue("delegate.rowBounds.offset",
                    RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit",
                    RowBounds.NO_ROW_LIMIT);
            Connection connection = (Connection) invocation.getArgs()[0];

            // 获得总记录数
            int totalCount = SqlHelper.getTotalCount(sql, connection, mappedStatement, boundSql);

            PAGINATION_TOTAL.set(totalCount);
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
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
