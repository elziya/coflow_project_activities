package com.technokratos.util.mapper;

import com.technokratos.dto.request.AccountNamesRequest;
import com.technokratos.dto.request.AccountRequest;

import com.technokratos.dto.request.SignUpForm;
import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.dto.response.StudentExtendedResponse;
import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AccountMapper {

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "codes", ignore = true)
    @Mapping(target = "state", ignore = true)
    AccountEntity toEntity(AccountRequest accountRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "codes", ignore = true)
    @Mapping(target = "state", ignore = true)
    AccountEntity toEntity(AccountNamesRequest accountNamesRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "codes", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "roles", ignore = true)
    AccountEntity signUpFormToEntity(SignUpForm signUpForm);

    AccountExtendedResponse toResponse(AccountEntity teacher);

    @Mapping(target = "educationCategory", ignore = true)
    StudentExtendedResponse toStudentExtendedResponse(AccountEntity account);
}

