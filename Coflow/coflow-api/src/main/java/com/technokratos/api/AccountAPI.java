package com.technokratos.api;

import com.technokratos.dto.request.AccountNamesRequest;
import com.technokratos.dto.request.AccountPasswordRequest;
import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.dto.response.ExceptionMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/users")
public interface AccountAPI<PRINCIPAL> {

    @ApiOperation(value = "Update account's first name and last name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated",
            response = AccountExtendedResponse.class),
            @ApiResponse(code = 404, message = "Account not found",
                    response = ExceptionMessage.class),
            @ApiResponse(code = 403, message = "Access to updating account is denied",
                    response = ExceptionMessage.class)
    })
    @PutMapping(value = "/{user-id}/names", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AccountExtendedResponse updateAccount(PRINCIPAL user, @PathVariable("user-id") UUID userId,
                                          @RequestBody @Valid AccountNamesRequest newData);

    @ApiOperation(value = "Update account's password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated",
                    response = AccountExtendedResponse.class),
            @ApiResponse(code = 404, message = "Account not found",
                    response = ExceptionMessage.class),
            @ApiResponse(code = 403, message = "Access to updating account is denied",
                    response = ExceptionMessage.class)
    })
    @PutMapping(value = "/{user-id}/password", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AccountExtendedResponse updateAccount(PRINCIPAL user, @PathVariable("user-id") UUID userId,
                                          @RequestBody @Valid AccountPasswordRequest newData);

    @ApiOperation(value = "Get account's information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got account's information",
                    response = AccountExtendedResponse.class),
            @ApiResponse(code = 404, message = "Account not found",
                    response = ExceptionMessage.class),
    })
    @GetMapping(value = "/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AccountExtendedResponse getAccount(@PathVariable("user-id") UUID userId);
}
