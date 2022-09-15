package com.gocheeta.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gocheeta.api.entity.Customer;
import com.gocheeta.api.entity.Driver;
import com.gocheeta.api.service.CustomerService;
import com.gocheeta.api.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DriverService driverService;

    @Value("${superAdmin.email}")
    private String superAdminEmail;

    @Value("${superAdmin.password}")
    private String superAdminPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.getUserByEmail(username);
        if (customer == null) {
            Driver driverInfo = driverService.getDriverByEmail(username);
            if (driverInfo == null) {
                if (username.equals(this.superAdminEmail)) {
                    UserDetails adminUser = this.getGoCheetaAdminDetails();
                    List<GrantedAuthority> superAdminRoles = new ArrayList<GrantedAuthority>();
                    superAdminRoles.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
                    return new org.springframework.security.core.userdetails.User(adminUser.getUsername(), adminUser.getPassword(), superAdminRoles);
                } else {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
            }
            List<GrantedAuthority> driverRoles = new ArrayList<GrantedAuthority>();
            driverRoles.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
            return new org.springframework.security.core.userdetails.User(driverInfo.getEmail(), driverInfo.getPassword(), driverRoles);
        }
        List<GrantedAuthority> customerRoles = new ArrayList<GrantedAuthority>();
        customerRoles.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), customerRoles);
    }

    private UserDetails getGoCheetaAdminDetails() {
        return new User(this.superAdminEmail, this.superAdminPassword, new ArrayList<>());
    }

}
