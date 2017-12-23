package com.wcc.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.Collection;

@RestController
// URL's prefixed /admin will reach this controller.
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Admin createNewAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @RequestMapping(value = "/{adminId}", method = RequestMethod.GET)
    public Admin getAdminById(@PathVariable Long adminId) {
        return adminRepository.findOne(adminId);
    }

    @RequestMapping(value = "/{adminId}", method = RequestMethod.PUT)
    public Admin updateAdminDetails(@PathVariable Long adminId, @RequestBody Admin adminIn) {
        Admin currAdmin = adminRepository.findOne(adminId);
        currAdmin.setUsername(adminIn.getUsername() != null ? adminIn.getUsername() : currAdmin.getUsername());
        currAdmin.setPassword(adminIn.getPassword() != null ? adminIn.getPassword() : currAdmin.getPassword());
        currAdmin.setPerms(adminIn.getPerms() != null ? adminIn.getPerms() : currAdmin.getPerms());
        currAdmin.setSuperUser(adminIn.isSuperUser());

        // Updating all fields to database
        adminRepository.save(currAdmin);

        return currAdmin;
    }

    @RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
    public Collection<Admin> findAdminByUsername(@PathVariable String query) {
        return adminRepository.findAdminByUsername(query);
    }
}
