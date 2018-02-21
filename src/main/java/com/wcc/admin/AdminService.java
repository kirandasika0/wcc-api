package com.wcc.admin;

import java.util.Collection;

public interface AdminService {
    /*
    * Find an admin by their username
    @param query a string that represents the target username
    @return Collection<Admin>
     */
    Collection<Admin> findAdminByUsername(String query);

    /*
    * Find one admin based on target username.
    @param query a string that represents the target username
    @return Admin
     */
    Admin findOneAdminByUsername(String query);

    /*
    * Check whether an admin has a given perm
    @param adminIn an instance of an admin to check perm on
    @param perm the perm to be checked
    @return boolean
     */
    boolean hasPerm(Admin adminIn, String perm);
}
