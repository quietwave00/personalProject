package blog.jwt;

//인증

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import blog.auth.PrincipalDetails;
import blog.web.user.dto.request.LoginRequestDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


//login POST 요청하면 이 필터 동작(AuthorizationFilter 다음)

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    //로그인 시도하면 실행됨
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("AuthenticationFilter 요청, 로그인 시도 중");

        try {
            ObjectMapper om = new ObjectMapper(); //json으로 들어오는 값
            LoginRequestDto loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
            log.info("user: {}", loginRequestDto);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUserId(), loginRequestDto.getPassword());
            log.info("authenticationToken: {}", authenticationToken);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            log.info("authentication: {}", authentication);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("로그인 완료: {}", principalDetails.getUser().getUserId());


            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    //attemptAuthentication() 실행 후 인증 정상적으로 되었으면 이 메소드 실행
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("실행 2");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("token ")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 10 * 10)))
                .withClaim("id", principalDetails.getUser().getUserId())
                .withClaim("password", principalDetails.getUser().getPassword())
                .sign(Algorithm.HMAC512("blog"));

        response.addHeader("Authorization", "Bearer " + jwtToken);

    }
}
