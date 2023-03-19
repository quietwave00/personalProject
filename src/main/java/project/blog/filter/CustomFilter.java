package project.blog.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.LogRecord;

@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        if(req.getRequestURI().equals("/blog/login")) {
            log.info("customFilter POST 요청됨");

            String headerAuth = req.getHeader("Authorization");
            log.info("headerAuth: {}", headerAuth);

            if(headerAuth.equals("blog")) {
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증 안 됨");
            }
        }

        chain.doFilter(req, res);


    }
}
