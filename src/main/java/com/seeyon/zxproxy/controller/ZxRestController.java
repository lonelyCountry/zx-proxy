package com.seeyon.zxproxy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhuqiwei
 * 2018/9/7.
 */
@RequestMapping("/seeyon")
@Controller
@ResponseBody
public class ZxRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZxRestController.class);
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/uc/rest.do")
    public String testIp(HttpServletRequest request) {
        String result = restTemplate.getForObject(parseRequest(request), String.class);
        LOGGER.info(result);
        return result;
    }

    /**
     * 解析request成url
     * @param request request
     * @return 访问oa的url
     */
    private String parseRequest(HttpServletRequest request) {
        Map<String, String[]> requestMap =  request.getParameterMap();
        //初始url
        StringBuilder baseUrl = new StringBuilder("http://10.3.4.172:8080/seeyon/uc/rest.do?");
        //拼接后的url
        requestMap.forEach((k, v)-> baseUrl.append(k).append("=").append(v[0]).append("&"));
        String finalUrl = baseUrl.substring(0, baseUrl.length() - 1);

        LOGGER.info("finalUrl=" + finalUrl);
        return finalUrl;
    }


}
