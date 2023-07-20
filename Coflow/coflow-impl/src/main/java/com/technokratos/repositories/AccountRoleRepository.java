package com.technokratos.repositories;

import com.technokratos.models.AccountRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRoleRepository extends JpaRepository<AccountRoleEntity, UUID> {

    Optional<AccountRoleEntity> findByAccount_IdAndCourse_Id(UUID accountId, UUID courseId);

    List<AccountRoleEntity> findByAccount_IdAndRole_Id(UUID accountId, UUID roleId);

    List<AccountRoleEntity> findByCourse_Id(UUID courseId);
}

