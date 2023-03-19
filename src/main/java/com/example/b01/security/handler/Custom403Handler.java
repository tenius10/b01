package com.example.b01.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class Custom403Handler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse resp,
                       AccessDeniedException accessDeniedException)throws IOException, ServletException {
        log.info("----------- ACCESS DENIED -----------");
        resp.setStatus(HttpStatus.FORBIDDEN.value());

        //JSON 요청이었는지 확인
        String contentType=req.getHeader("Content-type");
        boolean jsonRequest=contentType.startsWith("application/json");
        log.info("isJSON: "+jsonRequest);

        //일반 request
        if(!jsonRequest){
            resp.sendRedirect("/board/list?error=ACCESS_DENIED");
        }
    }
}
