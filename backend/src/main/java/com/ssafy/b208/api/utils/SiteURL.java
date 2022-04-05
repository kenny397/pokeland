package com.ssafy.b208.api.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SiteURL {
    public String getSiteURL(String removeStr, HttpServletRequest request) {
        return request.getRequestURL().toString().replace(removeStr,"");
    }
}
