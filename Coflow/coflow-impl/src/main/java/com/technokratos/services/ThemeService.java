package com.technokratos.services;

import com.technokratos.dto.request.ThemeCreationForm;
import com.technokratos.models.CourseEntity;
import com.technokratos.models.ThemeEntity;

import java.util.UUID;

public interface ThemeService {

    UUID addTheme(CourseEntity course, ThemeCreationForm form);

    ThemeEntity getThemeById(UUID themeId);
}
