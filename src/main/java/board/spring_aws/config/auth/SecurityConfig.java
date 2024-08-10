package board.spring_aws.config.auth;

import board.spring_aws.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // H2 콘솔 사용을 위한 옵션 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // 지정된 경로에 대한 접근 허용
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name()) // 특정 역할을 가진 사용자만 접근 가능
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리디렉션할 URL
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // OAuth2 사용자 정보 서비스 설정
                        )
                );

        return http.build();
    }
}