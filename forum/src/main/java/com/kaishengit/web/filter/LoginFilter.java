package com.kaishengit.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LoginFilter extends AbstractFilter {
    private List<String> urlList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String validateUrl = filterConfig.getInitParameter("validateUrl");
        urlList = Arrays.asList(validateUrl.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取用户要访问的URL
        String requestUrl = request.getRequestURI();

        if(urlList != null && urlList.contains(requestUrl)){
            if(request.getSession().getAttribute("curr_user") == null){
                //requestURL后如果有参数要获得
                Map map = request.getParameterMap();
                Set paramSet = map.entrySet();

                Iterator iterator = paramSet.iterator();
                if(iterator.hasNext()){
                    requestUrl += "?";

                    //当参数不止一个时
                    while (iterator.hasNext()){
                        Map.Entry entry = (Map.Entry) iterator.next();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        String valueString[] = (String[]) value;

                        String param = "";
                        for (int i = 0;i < valueString.length;i++){
                            param = key + "=" + valueString[i] + "&";
                            requestUrl += param;
                        }
                    }
                    requestUrl = requestUrl.substring(0,requestUrl.length() - 1);
                }

                //去登录页
                response.sendRedirect("/login?redirect="+requestUrl);
            }else {
                filterChain.doFilter(request,response);
            }
        }else {
            filterChain.doFilter(request,response);
        }

    }
}
