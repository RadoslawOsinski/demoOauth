package com.example.soc.demo.connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.*;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.SessionUserIdSource;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radosław Osiński
 */
@EnableSocial
@Configuration
public class SocialConfig implements SocialConfigurer {

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        if (environment.getProperty("app.social.login.enabled.facebook", Boolean.class, Boolean.FALSE)) {
            String appId = environment.getProperty("app.social.facebook.appId");
            String appSecret = environment.getProperty("app.social.facebook.appSecret");
            connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
        }
        if (environment.getProperty("app.social.login.enabled.twitter", Boolean.class, Boolean.FALSE)) {
            String appId = environment.getProperty("app.social.twitter.appId");
            String appSecret = environment.getProperty("app.social.twitter.appSecret");
            connectionFactoryConfigurer.addConnectionFactory(new TwitterConnectionFactory(appId, appSecret));
        }
        if (environment.getProperty("app.social.login.enabled.google", Boolean.class, Boolean.FALSE)) {
            throw new RuntimeException("no google!");
        }
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new SessionUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        InMemoryUsersConnectionRepository usersConnectionRepository = new InMemoryUsersConnectionRepository(connectionFactoryLocator);
        usersConnectionRepository.setConnectionSignUp(new ConnectionSignUp() {
            @Override
            public String execute(Connection<?> connection) {
                System.out.println("ConnectionSignUp connection: " + connection);
                return connection.getKey().toString();
            }
        });
        return usersConnectionRepository;
    }

    @Bean
    public ConnectController connectController(
            ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository, Environment environment) {
        ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
        connectController.setApplicationUrl(environment.getProperty("application.url"));
        return connectController;
    }

    @Bean
    public SocialUserDetailsService getSocialUserDetailsService() {
        SocialUserDetailsService socialUserDetailsService = new SocialUserDetailsService() {
            @Override
            public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
                System.out.println("loadUserByUserId userId: " + userId);
                if (userId.equals("twitter:11111")) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    return new SocialUser("radoTwitter", "pass", authorities);
                } else if (userId.equals("facebook:22222")) {
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                        return new SocialUser("radoFace", "pass", authorities);
                } else {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    return new SocialUser("rado2", "pass", authorities);
                }
            }
        };
        return socialUserDetailsService;
    }

}
