package com.ssafy.b208.api.utils;

import javax.servlet.http.HttpServletRequest;

public class SiteURL {
    public String getSiteURL(String removeStr, HttpServletRequest request) {
        return request.getRequestURL().toString().replace(removeStr,"");
    }
}
