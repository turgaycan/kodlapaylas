package com.kp.config;

import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

/**
 * Created by tcan on 01/01/16.
 */
public class ArrayPostgreSQLDialect extends PostgreSQL9Dialect {

    public ArrayPostgreSQLDialect() {
        super();

        /**
         * For other type array you can change integer[] to that array type
         */

        this.registerColumnType(Types.ARRAY, "integer[]");
    }

}
