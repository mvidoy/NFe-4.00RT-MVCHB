/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.backend.services;

import org.hibernate.EmptyInterceptor;

/**
 *
 * @author Osvaldo Vidoy
 */
public class TableNameReplacer extends EmptyInterceptor {

    public static final String TABLE_PLACEHOLDER = "{person_table_placeholder}";

    @Override
    public String onPrepareStatement(String sql) {
        if (sql.contains(TABLE_PLACEHOLDER )) {
            String replacement = "{your logic to fill proper table name}";
            sql = sql.replace(TABLE_PLACEHOLDER, replacement);
        }
        return super.onPrepareStatement(sql);
    }
}
