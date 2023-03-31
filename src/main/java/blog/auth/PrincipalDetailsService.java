package blog.auth;

import blog.utils.dto.ApiError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.web.user.repository.UserRepository;


//원래 /login일 때 동작하는데
//formLogin().disable() 해서 동작 안 함
//그래서 JwtAuthenticationFilter를 만들어서 이 필터를 타게 함

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return authentication;
            }
        };
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("loadUserByUsername() 실행");
        User userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
        return new PrincipalDetails(userEntity);
    }
}
