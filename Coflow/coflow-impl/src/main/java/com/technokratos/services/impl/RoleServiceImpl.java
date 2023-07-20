package com.technokratos.services.impl;

import com.technokratos.dto.enums.Role;
import com.technokratos.exceptions.CoflowRoleNotFondException;
import com.technokratos.models.RoleEntity;
import com.technokratos.repositories.RoleRepository;
import com.technokratos.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity getByRole(Role role) {
        return roleRepository.findByRole(role).orElseThrow(CoflowRoleNotFondException::new);
    }
}
