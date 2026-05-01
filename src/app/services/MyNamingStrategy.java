/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 *
 * @author Osvaldo Vidoy
 */
public class MyNamingStrategy extends DefaultNamingStrategy {

    @Override
    public String tableName(String tableName) {
        return tableName;
    }
}
