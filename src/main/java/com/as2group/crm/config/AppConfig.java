package com.as2group.crm.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.as2group.crm.enumeration.EmployeeStatus;
import com.as2group.crm.model.Employee;
import com.as2group.crm.model.Role;
import com.as2group.crm.repository.EmployeeRepository;
import com.as2group.crm.repository.RoleRepository;

@Configuration
public class AppConfig {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(AppConfig.class);

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    public void setRoles() {
        Role adm = new Role();
        adm.setId(Long.valueOf(1));
        adm.setLevel("ROLE_ADM");
        roleRepository.save(adm);
        Employee employeeAdm = new Employee();
        employeeAdm.setId(Long.valueOf(1));
        employeeAdm.setName("Kauã Borgarelli");
		employeeAdm.setEmail("kaua1as74@group");
		employeeAdm.setTelephone("12992002060");
		employeeAdm.setGender("Masculino");
		employeeAdm.setStatus(EmployeeStatus.ATIVO);
		employeeAdm.setEntryDate(LocalDate.now());
        employeeAdm.setPassword(encoder.encode("123456"));
        Set<Role> admRole = new HashSet<>();
        admRole.add(adm);
        employeeAdm.setRoles(admRole);
        employeeRepository.save(employeeAdm);
        logger.info("Role ADM created");

        Role sup = new Role();
        sup.setId(Long.valueOf(2));
        sup.setLevel("ROLE_EMPLOYEE");
        roleRepository.save(sup);
        logger.info("Role Employee created");
    }
}
