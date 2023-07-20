package com.technokratos.services.impl;

import com.technokratos.dto.request.ThemeCreationForm;
import com.technokratos.exceptions.CoflowThemeNotFoundException;
import com.technokratos.models.CourseEntity;
import com.technokratos.models.ThemeEntity;
import com.technokratos.repositories.ThemeRepository;
import com.technokratos.services.ThemeService;
import com.technokratos.util.mapper.ThemeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    private final ThemeMapper themeMapper;

    @Override
    public UUID addTheme(CourseEntity course, ThemeCreationForm form) {
        ThemeEntity theme = themeMapper.formToEntity(form);
        theme.setCourse(course);
        return themeRepository.save(theme).getId();
    }

    @Override
    public ThemeEntity getThemeById(UUID themeId) {
        return themeRepository.findById(themeId).orElseThrow(CoflowThemeNotFoundException::new);
    }
}
