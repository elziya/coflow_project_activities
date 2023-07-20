package com.technokratos.api;

import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.dto.response.CoursesPage;
import com.technokratos.dto.response.ExceptionMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/search/courses")
public interface SearchCoursesAPI {

    @ApiOperation(value = "Get new courses on a specific page")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got new courses", response = AccountExtendedResponse.class)
    })
    @GetMapping(value = "/new", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CoursesPage getNewCourses(@RequestParam("page") int page);


    @ApiOperation(value = "Get popular courses on a specific page")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got popular courses", response = AccountExtendedResponse.class)
    })
    @GetMapping(value = "/popular", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CoursesPage getPopularCourses(@RequestParam("page") int page);

    @ApiOperation(value = "Get courses on a specific page with name which contains string from request parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got courses with name which contains specific string",
                    response = AccountExtendedResponse.class)
    })
    @GetMapping(value = "/by-name", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CoursesPage getCoursesByName(@RequestParam("page") int page, @RequestParam("name") String name);

    @ApiOperation(value = "Get courses on a specific page on a specific language")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got courses on a specific language",
                    response = AccountExtendedResponse.class)
    })
    @GetMapping(value = "/by-lang", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CoursesPage getCoursesByLang(@RequestParam("page") int page, @RequestParam("lang") String language);

    @ApiOperation(value = "Get courses on a specific page on a specific language with name which contains string " +
            "from request parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got courses on a specific language with name which" +
                    " contains specific string",
                    response = AccountExtendedResponse.class)
    })
    @GetMapping(value = "/by-name-and-lang", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CoursesPage getCoursesByNameAndLang(@RequestParam("page") int page, @RequestParam("name") String name,
                                  @RequestParam("lang") String language);
}
