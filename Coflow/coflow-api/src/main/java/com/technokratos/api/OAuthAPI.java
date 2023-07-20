package com.technokratos.api;

import com.technokratos.dto.oAuth.VkRequest;
import com.technokratos.dto.response.TokenCoupleResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/auth")
public interface OAuthAPI {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/vk")
    VkRequest auth();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/vk/success", produces = APPLICATION_JSON_VALUE)
    TokenCoupleResponse receiveCode(@Param("code") String code);
}
