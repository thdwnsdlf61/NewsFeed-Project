//package com.example.newsfeed.common.config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.PatternMatchUtils;
//
//import java.io.IOException;
//
//@Slf4j
//@RequiredArgsConstructor
//public class LoginFilter implements Filter {
//
//    private static final String[] WHITE_LIST = {"/", "/signup", "/login", "/logout"};
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String requestURI = httpRequest.getRequestURI();
//
//        log.info("로그인 필터 로직 실행");
//
//        if (!isWhiteList(requestURI)) {
//            HttpSession session = httpRequest.getSession(false);
//
//            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
//                throw new RuntimeException("로그인 해주세요.");
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    public boolean isWhiteList(String requestURI) {
//        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
//    }
//}
