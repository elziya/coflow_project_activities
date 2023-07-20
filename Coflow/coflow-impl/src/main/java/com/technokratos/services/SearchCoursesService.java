package com.technokratos.services;

import com.technokratos.dto.response.CoursesPage;

public interface SearchCoursesService {

    CoursesPage getNewCourses(int page);

    CoursesPage getPopularCourses(int page);

    CoursesPage getCoursesByNameAndLang(int page, String name, String language);

    CoursesPage getCoursesByName(int page, String name);

    CoursesPage getCoursesByLang(int page, String language);
}
