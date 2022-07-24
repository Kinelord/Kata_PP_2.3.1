package org.example.config;

import org.hibernate.dialect.MySQL8Dialect;

public class CustomMySQLDialect extends MySQL8Dialect {
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
