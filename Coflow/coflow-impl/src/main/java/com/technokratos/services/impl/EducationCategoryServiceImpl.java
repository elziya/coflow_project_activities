package com.technokratos.services.impl;

import com.technokratos.dto.enums.EducationCategory;
import com.technokratos.exceptions.CoflowEducationCategoryNotFoundException;
import com.technokratos.models.EducationCategoryEntity;
import com.technokratos.repositories.EducationCategoryRepository;
import com.technokratos.services.EducationCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EducationCategoryServiceImpl implements EducationCategoryService {

    private final EducationCategoryRepository educationCategoryRepository;

    @Override
    public EducationCategoryEntity getEducationCategoryByName(EducationCategory name) {
        return educationCategoryRepository.findByName(name).orElseThrow(CoflowEducationCategoryNotFoundException::new);
    }
}
