package com.technokratos.services.impl;

import com.technokratos.dto.enums.Role;
import com.technokratos.dto.request.TeacherRequest;
import com.technokratos.dto.response.TeacherExtendedResponse;
import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.exceptions.CoflowAccessDeniedException;
import com.technokratos.exceptions.CoflowTeacherAlreadyExistsException;
import com.technokratos.exceptions.CoflowTeacherNotFoundException;
import com.technokratos.models.TeacherEntity;
import com.technokratos.repositories.TeacherRepository;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.AccountRoleService;
import com.technokratos.services.TeacherService;
import com.technokratos.util.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final AccountRoleService accountRoleService;

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    @Override
    public UUID addTeacher(AccountUserDetails user, TeacherRequest teacher) {

        if (teacherRepository.findByAccount_Id(user.getAccount().getId()).isPresent()){
            throw new CoflowTeacherAlreadyExistsException();
        }

        TeacherEntity teacherEntity = teacherMapper.toEntity(teacher);
        teacherEntity.setAccount(user.getAccount());

        return teacherRepository.save(teacherEntity).getId();
    }

    @Override
    public TeacherResponse updateTeacher(AccountUserDetails user, UUID teacherId, TeacherRequest newData) {
        TeacherEntity teacher = teacherRepository.findById(teacherId).orElseThrow(CoflowTeacherNotFoundException::new);

        if(teacher.getAccount().getId() != user.getAccount().getId()){
            throw new CoflowAccessDeniedException("Access to updating teacher is denied");
        }

        teacher.setInfo(newData.getInfo());

        return teacherMapper.toResponse(teacherRepository.save(teacher));
    }

    @Override
    public TeacherExtendedResponse getTeacher(UUID teacherId) {
        TeacherEntity teacher = teacherRepository.findById(teacherId).orElseThrow(CoflowTeacherNotFoundException::new);
        TeacherExtendedResponse teacherExtendedResponse = teacherMapper.toExtendedResponse(teacher);
        teacherExtendedResponse.setCourses(accountRoleService.getTeachingCourses(teacher.getAccount().getId()));

        return teacherExtendedResponse;
    }

    @Override
    public TeacherEntity getTeacherByAccountId(UUID accountId) {
        return teacherRepository.findByAccount_Id(accountId).orElseThrow(CoflowTeacherNotFoundException::new);
    }

    @Override
    public Set<TeacherResponse> getCourseTeachers(UUID courseId) {
        return accountRoleService.getCoursesRolesInfo(courseId)
                .stream()
                .filter(r -> (Role.TEACHER.equals(r.getRole().getRole()) || Role.ADMIN.equals(r.getRole().getRole())))
                .map(r -> teacherMapper.toResponse(
                        teacherRepository.findByAccount_Id(r.getAccount().getId())
                                .orElseThrow(CoflowTeacherNotFoundException::new))
                )
                .collect(Collectors.toSet());
    }
}

