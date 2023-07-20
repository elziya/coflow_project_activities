package com.technokratos.util.email;

import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.exceptions.CoflowTemplatesMethodException;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.CourseEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.technokratos.consts.CoflowCommonConstants.*;

@RequiredArgsConstructor
@Component
public class FreemarkerEmailGenerator implements EmailGenerator {

    private final FreeMarkerConfigurer configurer;

    @Override
    public String generateConfirmationMail(String username, String confirmCode) {
        Template emailConfirmationTemplate;
        try {
            emailConfirmationTemplate = configurer.getConfiguration().getTemplate("confirm_mail.ftlh");
        } catch (IOException e) {
            throw new CoflowTemplatesMethodException();
        }

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("confirm_code", confirmCode);

        String result = "";

        try (StringWriter writer = new StringWriter()) {
            emailConfirmationTemplate.process(data, writer);
            result = writer.toString();
        } catch (IOException | TemplateException e) {
            throw new CoflowTemplatesMethodException();
        }

        return result;
    }

    @Override
    public File generateCertificate(AccountEntity account, CourseEntity course, Instant instant) {
        Map<String, Object> data = new HashMap<>();
        data.put("firstName", account.getFirstName());
        data.put("lastName", account.getLastName());
        data.put("courseName", course.getName());
        data.put("themesNumber", course.getThemes().size());
        data.put("completionDate", String.valueOf(instant).substring(0, 10));
        data.put("forWhom", course.getForWhom());

        Template certificateTemplate = null;
        String content = "";
        File image = null;

        try {
            certificateTemplate = configurer.getConfiguration().getTemplate("finished_course_certificate.ftlh");
            content = FreeMarkerTemplateUtils.processTemplateIntoString(certificateTemplate, data);
            image = HtmlToImageUtil.convertXhtmlToImage(content, CERTIFICATE_INPUT_FILE_NAME,
                    CERTIFICATE_OUTPUT_FILE_NAME, CERTIFICATE_WIDTH, CERTIFICATE_HEIGHT);
        } catch (IOException | TemplateException e) {
            throw new CoflowTemplatesMethodException();
        }

        return image;
    }
}

