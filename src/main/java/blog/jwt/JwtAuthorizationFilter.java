package blog.jwt;


import blog.utils.dto.ApiError;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import blog.auth.PrincipalDetails;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.web.user.repository.UserRepository;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//권한, 인증 필요한 주소 요청 시 해당 필터 거침

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
      log.info("AuthorizationFilter 요청");

      String jwtHeader = request.getHeader("Authorization");

      //헤더 확인
      if(jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
          log.info("token: {}", jwtHeader);
          log.info("header 오류");
          chain.doFilter(request, response);
          return;
      }

      //토큰 검증하여 정상 사용자인지 확인
      String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
      log.info("jwtToken: {}", jwtToken);
      String userId = JWT.require(Algorithm.HMAC512("blog")).build().verify(jwtToken).getClaim("id").asString();
      log.info("userId: {}", userId);
      //서명 완료
        if(userId != null) {
            log.info("서명 완료");
            User userEntity = userRepository.findByUserId(userId)
                    .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
            log.info("findByUserId 완료");
            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("인증완료");
            chain.doFilter(request, response);
        }
    }


}
