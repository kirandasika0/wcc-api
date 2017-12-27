package com.wcc.admin;

import java.util.Collection;

import com.wcc.error.LoginFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        currAdmin.setUsername((adminIn.getUsername() != null) ? adminIn.getUsername() : currAdmin.getUsername());
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Admin loginAdminWithUsernameAndPassword(@RequestBody Admin adminPayload) throws LoginFailureException {
        if (adminPayload.getUsername() == null || adminPayload.getUsername().length() == 0) {
            throw new LoginFailureException("username and password required");
        }
        if (adminPayload.getPassword() == null || adminPayload.getPassword().length() == 0) {
            throw new LoginFailureException("username and password required");
        }

        Admin dbAdmin = adminRepository.findOneAdminByUsername(adminPayload.getUsername());
        if (dbAdmin == null) {
            throw new LoginFailureException("username not found.");
        }

        // Login validation
        if (!adminPayload.getUsername().equals(dbAdmin.getUsername()) || !adminPayload.getPassword().equals(dbAdmin.getPassword())) {
            throw new LoginFailureException("invalid login credentials");
        }

        return dbAdmin;
    }
}
