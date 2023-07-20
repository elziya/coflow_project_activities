package com.technokratos.services.impl;

import com.technokratos.dto.enums.Role;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.exceptions.CoflowAccountRoleInfoNotFoundException;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.AccountRoleEntity;
import com.technokratos.models.CourseEntity;
import com.technokratos.repositories.AccountRoleRepository;
import com.technokratos.services.AccountRoleService;
import com.technokratos.services.RoleService;
import com.technokratos.util.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountRoleServiceImpl implements AccountRoleService {

    private final RoleService roleService;

    private final AccountRoleRepository accountRoleRepository;

    private final CourseMapper courseMapper;

    @Override
    public void changeAccountsRoleOnCourse(AccountEntity account, CourseEntity course, Role role) {
        Optional<AccountRoleEntity> accountRoleEntity = accountRoleRepository
                .findByAccount_IdAndCourse_Id(account.getId(), course.getId());

        AccountRoleEntity updatedRole = null;
        if (accountRoleEntity.isPresent()) {
            updatedRole = accountRoleEntity.get();
            updatedRole.setRole(roleService.getByRole(role));
        } else {
            updatedRole = new AccountRoleEntity(account, course, roleService.getByRole(role));
        }

        accountRoleRepository.save(updatedRole);
    }

    @Override
    public void setSiteAccountRole(AccountEntity account, Role role) {
        accountRoleRepository.save(new AccountRoleEntity(account, null, roleService.getByRole(role)));
    }

    @Override
    public AccountRoleEntity getAccountOnCourseRoleInfo(UUID accountId, UUID courseId) {
        return accountRoleRepository.findByAccount_IdAndCourse_Id(accountId, courseId)
                .orElseThrow(CoflowAccountRoleInfoNotFoundException::new);
    }

    @Override
    public Optional<AccountRoleEntity> findAccountOnCourseRoleInfo(UUID accountId, UUID courseId) {
        return accountRoleRepository.findByAccount_IdAndCourse_Id(accountId, courseId);
    }

    @Override
    public Set<CourseResponse> getTeachingCourses(UUID accountId) {
        Set<CourseResponse> teaching = accountRoleRepository.findByAccount_IdAndRole_Id(accountId, roleService.getByRole(Role.TEACHER).getId())
                .stream()
                .map(r -> courseMapper.toResponse(r.getCourse()))
                .collect(Collectors.toSet());
        Set<CourseResponse> administrating = accountRoleRepository.findByAccount_IdAndRole_Id(accountId, roleService.getByRole(Role.ADMIN).getId())
                .stream()
                .map(r -> courseMapper.toResponse(r.getCourse()))
                .collect(Collectors.toSet());
        teaching.addAll(administrating);
        return teaching;
    }

    @Override
    public void deleteAccountCourseRoleInfo(AccountEntity account, CourseEntity course) {
        accountRoleRepository.delete(
                accountRoleRepository.findByAccount_IdAndCourse_Id(account.getId(), course.getId())
                        .orElseThrow(CoflowAccountRoleInfoNotFoundException::new)
        );
    }

    @Override
    public List<AccountRoleEntity> getCoursesRolesInfo(UUID courseId) {
        return accountRoleRepository.findByCourse_Id(courseId);
    }
}

