package com.technokratos.services.impl;

import com.technokratos.dto.enums.CourseType;
import com.technokratos.dto.response.CoursesPage;
import com.technokratos.dto.response.SearchCourseResponse;
import com.technokratos.models.CourseEntity;
import com.technokratos.repositories.CourseRepository;
import com.technokratos.services.EducationService;
import com.technokratos.services.SearchCoursesService;
import com.technokratos.services.TeacherService;
import com.technokratos.util.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchCoursesServiceImpl implements SearchCoursesService {

    private final CourseRepository courseRepository;

    private final TeacherService teacherService;

    private final EducationService educationService;

    private final CourseMapper courseMapper;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    @Override
    public CoursesPage getNewCourses(int page) {

        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by("createDate").descending());
        Page<CourseEntity> coursePage = courseRepository
                .findAllByCourseType_Name(CourseType.PUBLIC, pageRequest);

        return this.toCoursesPage(coursePage);
    }

    @Override
    public CoursesPage getCoursesByNameAndLang(int page, String name, String language) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<CourseEntity> coursePage = courseRepository.findAllByCourseType_NameAndNameLikeAndLanguage_Name(
                CourseType.PUBLIC, "%"+name+"%", language.toUpperCase(), pageRequest);

        return this.toCoursesPage(coursePage);
    }

    @Override
    public CoursesPage getCoursesByName(int page, String name) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<CourseEntity> coursePage = courseRepository.findAllByCourseType_NameAndNameLike(
                CourseType.PUBLIC, "%"+name+"%", pageRequest);

        return this.toCoursesPage(coursePage);
    }

    @Override
    public CoursesPage getCoursesByLang(int page, String language) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<CourseEntity> coursePage = courseRepository.findAllByCourseType_NameAndLanguage_Name(
                CourseType.PUBLIC, language.toUpperCase(), pageRequest);

        return this.toCoursesPage(coursePage);
    }

    private CoursesPage toCoursesPage(Page<CourseEntity> coursePage){
        List<SearchCourseResponse> searchCourseResponses = coursePage.getContent().stream()
                .map(courseMapper::toSearchResponse).collect(Collectors.toList());

        searchCourseResponses.forEach(c -> c.setTeachers(teacherService.getCourseTeachers(c.getId())));

        return CoursesPage.builder()
                .courses(searchCourseResponses)
                .totalPages(coursePage.getTotalPages())
                .build();
    }

    @Override
    public CoursesPage getPopularCourses(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);

        Page<CourseEntity> coursePage = courseRepository.findAllByCourseType_Name(CourseType.PUBLIC, pageRequest);

        coursePage.getContent().forEach(c -> c.setStudents(
                educationService.findByCourseId(c.getId())
        ));

        List<SearchCourseResponse> searchCourseResponses = coursePage.getContent().stream()
                .sorted(Comparator.comparing(c -> c.getStudents().size(), Comparator.reverseOrder()))
                .map(courseMapper::toSearchResponse).collect(Collectors.toList());

        searchCourseResponses.forEach(c -> c.setTeachers(teacherService.getCourseTeachers(c.getId())));

        return CoursesPage.builder()
                .courses(searchCourseResponses)
                .totalPages(coursePage.getTotalPages())
                .build();
    }
}
