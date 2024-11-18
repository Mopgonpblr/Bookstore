package com.andersen.bookstore.controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPDataSource {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;
    private static Properties properties;

    static {
        properties = DataControl.PROPERTIES;
        config.setJdbcUrl(properties.getProperty("url"));
        config.setDriverClassName(org.postgresql.Driver.class.getName());
        config.setUsername(properties.getProperty("username"));
        config.setPassword(properties.getProperty("password"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void setProperties(Properties properties){
        HikariCPDataSource.properties = properties;
    }
    private HikariCPDataSource(){}
}
