package blog.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtSetHolderFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtSetHolderFilter 실행");
        String token = jwtUtil.resolveToken(request);
        log.info("jwtUtil에서 가져온 token: {}", token);
        if(token != null) {
            String userId = jwtUtil.getUserIdFromToken(token);
            log.info("userId: {}", userId);
            UserContextHolder.setUserId(userId);
        }

        filterChain.doFilter(request, response);
    }
}
