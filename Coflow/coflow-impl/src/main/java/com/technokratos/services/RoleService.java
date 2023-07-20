package com.technokratos.services;

import com.technokratos.dto.enums.Role;
import com.technokratos.models.RoleEntity;

public interface RoleService {

    RoleEntity getByRole(Role role);
}
