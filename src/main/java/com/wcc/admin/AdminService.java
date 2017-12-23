package com.wcc.admin;

import java.util.Collection;

public interface AdminService {
    public Collection<Admin> findAdminByUsername(String query);
}
