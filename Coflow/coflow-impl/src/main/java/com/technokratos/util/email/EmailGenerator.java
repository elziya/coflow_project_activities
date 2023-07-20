package com.technokratos.util.email;

import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.CourseEntity;

import java.io.File;
import java.time.Instant;
import java.util.Set;

public interface EmailGenerator {

    String generateConfirmationMail(String username, String confirmCode);

    File generateCertificate(AccountEntity account, CourseEntity course, Instant instant);
}
