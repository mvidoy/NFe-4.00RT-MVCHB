package com.backend.services;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory((System.getProperty("tpAmb").equals("1")
                    ? "producaoPU"
                    : "homologacaoLOCAL"), getProperties());

    public EntityManager getConnection() {
        return factory.createEntityManager(
                getProperties());
    }

    public static Map getProperties() {
        Map result = new HashMap();
        HibernateInterceptor hibernateInterceptor = new HibernateInterceptor();
        result.put("hibernate.ejb.interceptor", hibernateInterceptor);
        return result;
    }
}
