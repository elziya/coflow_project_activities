package com.technokratos.controllers;

import com.technokratos.api.TeacherAPI;
import com.technokratos.dto.request.TeacherRequest;
import com.technokratos.dto.response.TeacherExtendedResponse;
import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TeacherController implements TeacherAPI<AccountUserDetails>  {

    private final TeacherService teacherService;

    @Override
    public UUID addTeacher(@AuthenticationPrincipal AccountUserDetails user, TeacherRequest teacher) {
        return teacherService.addTeacher(user, teacher);
    }

    @Override
    public TeacherResponse updateTeacher(@AuthenticationPrincipal AccountUserDetails user, UUID teacherId,
                                         TeacherRequest newData) {
        return teacherService.updateTeacher(user, teacherId, newData);
    }

    @Override
    public TeacherExtendedResponse getTeacher(UUID teacherId) {
        return teacherService.getTeacher(teacherId);
    }
}
