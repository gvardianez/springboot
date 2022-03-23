package ru.alov.springboot.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alov.springboot.entities.Role;
import ru.alov.springboot.repositories.RoleRepository;
import ru.alov.springboot.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

}
