package com.technokratos.util.oAuth;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GeneratingUriUtility {

    public static String generateURI(String uri, Map<String, String> parameters) {
        StringBuilder requestUri = new StringBuilder(uri);

        if (!parameters.isEmpty()) {
            requestUri.append("?");

            for (String key : parameters.keySet()) {
                requestUri.append(key).append("=").append(parameters.get(key)).append("&");
            }

            requestUri.deleteCharAt(requestUri.length() - 1);
        }

        return requestUri.toString();
    }
}
