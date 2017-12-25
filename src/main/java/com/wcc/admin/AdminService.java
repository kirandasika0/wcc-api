package com.wcc.admin;

import java.util.Collection;

public interface AdminService {
    Collection<Admin> findAdminByUsername(String query);
    Admin findOneAdminByUsername(String query);
}
