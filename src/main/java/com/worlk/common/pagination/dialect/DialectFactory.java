package com.worlk.common.pagination.dialect;

/**
 * Created by XSF on 14-3-24.
 */
public class DialectFactory
{
    public static Dialect buildDialect(DatabaseType databaseType)
    {
        switch (databaseType)
        {
            case MYSQL:
                return new MySQLDialect();
            case ORACLE:
                return new OracleDialect();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
