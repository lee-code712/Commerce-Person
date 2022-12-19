package com.digital.v3.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.digital.v3.context.AuthContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	public static List<String> authEssential = Arrays.asList("/rest/**");
	public static List<String> authInessential = Arrays.asList("/rest/person/signUp", "/rest/person/login", "/rest/person/logout");
    
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader("Authorization");
        log.info("preHandle: " + token);
        
        AuthContext authContext = new AuthContext();
        
        // token 값이 유효하지 않으면 401 Unauthorized 에러
        if (!authContext.tokenVaild(token)) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	response.setContentType("application/json");
        	response.setCharacterEncoding("UTF-8");
        	response.getWriter().write("{\"errorCode\":\"401\",\"errorMsg\":\"인증 토큰이 유효하지 않습니다.\"}");
        	return false;
        }
        
        // token 만료 여부 확인, 만료 시 자동 로그아웃
        if (authContext.expireCheck(token)) {
        	response.sendRedirect("/rest/person/logout");
        	return false;
        }
        
        // 만료되지 않았으면 token 유효 시간 갱신
        authContext.updateValidTime(token);
        return true;
    }
    
}
