package com.wcc.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Admin> findAdminByUsername(String query) {
        Stream<Admin> adminStream = StreamSupport.stream(
                adminRepository.findAll().spliterator(), false);

        return adminStream
                .filter(a -> a.getUsername().equals(query))
                .collect(Collectors.toList());
    }

    @Override
    public Admin findOneAdminByUsername(String query) {
        Stream<Admin> adminStream = StreamSupport.stream(adminRepository.findAll().spliterator(), false);

        Optional<Admin> admin = adminStream.filter(a -> a.getUsername().equals(query)).findFirst();

        return admin.isPresent() ? admin.get() : null;
    }

    @Override
    public boolean hasPerm(Admin adminIn, String perm) {
        return adminIn.getPerms().stream().anyMatch(adminPerm -> adminPerm.equals(perm));
    }
}
