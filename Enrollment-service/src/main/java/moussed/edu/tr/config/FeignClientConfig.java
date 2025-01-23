package moussed.edu.tr.config;


import feign.Feign;
import feign.RequestInterceptor;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .client(new ApacheHttpClient());
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = "Bearer " + getToken();
            requestTemplate.header("Authorization", token);
        };
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            Object credentials = authentication.getCredentials();
            if (credentials instanceof String) {
                return (String) credentials; // The token is stored in the credentials field
            }
        }
        throw new IllegalStateException("No JWT token found in SecurityContext");
    }

}

