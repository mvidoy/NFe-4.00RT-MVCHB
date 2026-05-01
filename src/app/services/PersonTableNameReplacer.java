/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import org.hibernate.EmptyInterceptor;

public class PersonTableNameReplacer extends EmptyInterceptor {

    public static final String TABLE_PLACEHOLDER = "cacores0";
    public String TABLE_PLACEHOLDER1 = "";

    public String PersonTableNameReplacer(final String str) {
        this.TABLE_PLACEHOLDER1 = str;
        return null;
    }

}
