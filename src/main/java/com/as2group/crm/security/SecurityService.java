package com.as2group.crm.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.as2group.crm.enumeration.EmployeeStatus;
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.EmployeeRepository;
import com.as2group.crm.util.RoleUtil;

@Service("securityService")
public class SecurityService implements UserDetailsService{
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <Employee> employeeOp = employeeRepository.findByEmail(username);
        if(employeeOp == null) {
            throw new UsernameNotFoundException("Employee not found");
        }

        Employee employee = employeeOp.get();

        if (employee.getStatus() == EmployeeStatus.INATIVO) {
        throw new DisabledException("Employee is inactive");
    }

        return User.builder().username(employee.getEmail()).password(employee.getPassword()).authorities(RoleUtil.parseAuthority(employee.getRoles())).build();
    }

}
