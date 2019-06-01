package com.mrbook.filter;

import com.mrbook.controller.base.ControllerRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class HomeFilter implements Filter {
    private ControllerRepository controllerRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.controllerRepository = ControllerRepository.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        if (!process((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean process(HttpServletRequest request, HttpServletResponse response) {
        if (request.getRequestURI().startsWith("/css") ||
                request.getRequestURI().startsWith("/images") ||
                request.getRequestURI().startsWith("/favicon")) {
            return false;
        }
        Set<String> urls = controllerRepository.getControllers().keySet();
        for (String url : urls) {
            System.out.println(url);
        }
        if (urls.contains(getRequestPath(request))) {
            controllerRepository.getControllers().get(getRequestPath(request)).process(request, response);
            System.out.println("ddddddddddddddddddddd");
            return true;
        }
        return false;
    }

    private String getRequestPath(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();

        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }

        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }
}
