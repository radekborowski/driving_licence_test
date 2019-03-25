package pracadom.driving_licence_test.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserService customUserService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserService customUserService, PasswordEncoder passwordEncoder) {
        this.customUserService = customUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests() //wlaczam autoryzacje
                .antMatchers("/login**", "/").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/signin") //form action link
                .usernameParameter("username") //input name
                .passwordParameter("password")//input password
                .successHandler(
                        (req, res, auth) -> {
                            for (GrantedAuthority g : auth.getAuthorities()) {
                                System.out.println(g.getAuthority());
                            }
                            res.sendRedirect("/");
                        }
                ).failureHandler(
                (req, res, exp) -> {
                    String errorMessage;
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        errorMessage = "Invalid username or password";
                    } else {
                        errorMessage = "unknown error" + exp.getMessage();
                    }
                    req.getSession().setAttribute("message:", errorMessage);
                    res.sendRedirect("/login");
                }
        )
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(
                        (req, res, auth) -> {
                            res.sendRedirect("/");
                        }
                ).permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/");//error handler 403

        http.csrf().disable();

        http.headers().frameOptions().disable(); //for H2 memory database
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder);
    }

}