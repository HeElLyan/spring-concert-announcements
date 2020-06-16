package ru.itis.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@ComponentScan("ru.itis")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .debug(true)
//                .ignoring()
//                .antMatchers("/images/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/**").hasAuthority("SUPERUSER")
                    .antMatchers("/user/**").authenticated()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .antMatchers("/superuser/**").hasAuthority("SUPERUSER")
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/plane").permitAll()
                    .antMatchers("/login").anonymous()
                    .anyRequest().authenticated()

                .and()
                    .formLogin()
                        .loginPage("/login")
                        .usernameParameter("login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        .permitAll()

                .and()
                    .rememberMe()
                        .rememberMeParameter("remember-me")
                        .tokenRepository(tokenRepository())

                .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("SESSION", "remember-me")
                        .invalidateHttpSession(true)
                        .permitAll();

        http.csrf().disable();

    }

//    private ClientRegistration getRegistration(){
//        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
//                .clientId("917317696983-g0dodg6no42m2t2g2vq95h5sbhp59frn.apps.googleusercontent.com")
//                .clientSecret("9-mR_lud80nEpQ8tSYSKVE4X")
//                .build();
//    }

//    public ClientRegistrationRepository  getClientRegistrationRepository(){
//        return  new InMemoryClientRegistrationRepository(getRegistration());
//    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(authenticationProvider);
    }
}
