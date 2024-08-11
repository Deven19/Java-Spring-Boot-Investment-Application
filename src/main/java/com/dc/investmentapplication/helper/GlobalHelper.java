package com.dc.investmentapplication.helper;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GlobalHelper {

    public static final ThreadLocal<SimpleDateFormat> threadSafeDatetimeFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("MM-dd-YYYY HH:mm:ss"));
    public static final ThreadLocal<SimpleDateFormat> threadSafeDatetimeFormatSQL =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("YYY-MM-dd HH:mm:ss"));
    public static final Logger logger = LoggerFactory.getLogger(GlobalHelper.class);

    public static Map<String, String> getHeadersMap(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        for (String headerName : Collections.list(request.getHeaderNames())) {
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }
}

