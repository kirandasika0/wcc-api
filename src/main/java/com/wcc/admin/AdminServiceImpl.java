package com.wcc.admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

public class AdminServiceImpl implements AdminService{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Collection<Admin> findAdminByUsername(String query) {
        Query query1= entityManager.createNativeQuery("SELECT * FROM wcc.admin WHERE username LIKE ?", Admin.class);
        query1.setParameter(1, query + "%");

        return query1.getResultList();
    }
}
