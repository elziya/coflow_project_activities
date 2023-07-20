package com.technokratos.services;

import com.technokratos.dto.enums.EducationCategory;
import com.technokratos.models.EducationCategoryEntity;

public interface EducationCategoryService {

    EducationCategoryEntity getEducationCategoryByName(EducationCategory name);
}
