package com.uniamerica.unijobsbackend.configs.application;

import com.uniamerica.unijobsbackend.Excessoes.RegraNegocioExcessao;
import com.uniamerica.unijobsbackend.auth.services.AuthService;
import com.uniamerica.unijobsbackend.models.Role;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.models.enums.RoleName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class Application {
    private final AuthService authService;

    @Value("${application.default-user.username}")
    private String defaultUserName;

    @Value("${application.default-user.password}")
    private String defaultUserPassword;

    @Bean
    public void configureDefaultUser() {
        try {
            authService.register(Usuario.builder()
                    .email(defaultUserName)
                    .senha(defaultUserPassword)
                    .ra("99999")
                    .nome("default user")
                    .roles(Set.of(Role.builder()
                            .name(RoleName.ROLE_ADMIN)
                            .build()))
                    .build());
        } catch (RegraNegocioExcessao e) {
            log.warn("default user creation failed");
        }
    }
}
