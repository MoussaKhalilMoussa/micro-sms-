package moussed.edu.tr.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import moussed.edu.tr.service.serviceImpl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;


import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityConfig {
    @Value("${keyStore.path}")
    private String keyStorePath;
    @Value("${keyStore.password}")
    private String keyStorePassword;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService) {
    }

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/**", "/user/**").permitAll()
                .anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtEncoder jwtEncoder() throws UnrecoverableKeyException, CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException {
        RSAKey rsaKey = loadRSAKer();
        JWKSource<SecurityContext> jwkSource = (jwtSelector,securityContext) -> jwtSelector.select(new JWKSet(rsaKey));
        return new NimbusJwtEncoder(jwkSource);
    }

    private RSAKey loadRSAKer() throws UnrecoverableKeyException, CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException{
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new ClassPathResource(keyStorePath).getInputStream(), keyStorePassword.toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey) keyStore.getCertificate("auth-server").getPublicKey();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey("auth-server", keyStorePassword.toCharArray());
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID("auth-server").build();
    }
}
