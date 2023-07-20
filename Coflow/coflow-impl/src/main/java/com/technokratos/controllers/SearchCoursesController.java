package com.technokratos.controllers;

import com.technokratos.api.SearchCoursesAPI;
import com.technokratos.dto.response.CoursesPage;
import com.technokratos.services.SearchCoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchCoursesController implements SearchCoursesAPI {

    private final SearchCoursesService searchCoursesService;

    @Override
    public CoursesPage getNewCourses(int page) {
        return searchCoursesService.getNewCourses(page);
    }

    @Override
    public CoursesPage getPopularCourses(int page) {
        return searchCoursesService.getPopularCourses(page);
    }

    @Override
    public CoursesPage getCoursesByName(int page, String name) {
        return searchCoursesService.getCoursesByName(page, name);
    }

    @Override
    public CoursesPage getCoursesByLang(int page, String language) {
        return searchCoursesService.getCoursesByLang(page, language);
    }

    @Override
    public CoursesPage getCoursesByNameAndLang(int page, String name, String language) {
        return searchCoursesService.getCoursesByNameAndLang(page, name, language);
    }
}
