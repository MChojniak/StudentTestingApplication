package chojniak.marcin.testysb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                        .csrf().ignoringAntMatchers("/h2-console/**")
                        .and().authorizeRequests()
                        .antMatchers("/favicon.ico","/reg/**","/css/**","/js/**","/bootstrap/**","/images/**","/login/**").permitAll()
                        .antMatchers("/**").authenticated()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .and().headers().frameOptions().sameOrigin()
                        .and()
                        .formLogin()
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                            .failureUrl("/login?error=true")
                            .permitAll()
                        .and()
                            .logout()
                            .logoutSuccessUrl("/login?logout=true")
                            .invalidateHttpSession(true)
                            .permitAll();
    }
}
