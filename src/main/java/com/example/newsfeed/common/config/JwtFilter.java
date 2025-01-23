package com.example.newsfeed.common.config;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/signup", "/login", "/logout"};

    private final JwtTokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        log.info("로그인 로직 실행");

        if (!isWhiteList(requestURI)) {

            String token = httpRequest.getHeader("Authorization");

            if (token == null) {
                throw new RuntimeException("로그인 해주세요.");
            }

            DecodedJWT decodedJWT = tokenProvider.verifiedToken(token);

            String getUserId = decodedJWT.getSubject();
            long userId = Long.parseLong(getUserId);

            request.setAttribute("userId", userId);

//            request.setAttribute("email", decodedJWT.getClaim("email"));

        }

        chain.doFilter(request, response);
    }

    public boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
