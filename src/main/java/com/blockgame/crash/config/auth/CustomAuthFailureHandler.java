package com.blockgame.crash.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler{

    private final String DEFAULT_FAILURE_URL = "/account/login";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

                String errorMessage = null;

                if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
                    errorMessage = "아이디 혹은 비밀번호가 올바르지 않습니다.";
                }
                else if(exception instanceof DisabledException){
                    errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
                }
                else{
                    errorMessage = "예상치 못한 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
                }

                request.setAttribute("errorMessage", errorMessage);

                System.out.println("errorMessage" + errorMessage);

                request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
        
    }
    
}
