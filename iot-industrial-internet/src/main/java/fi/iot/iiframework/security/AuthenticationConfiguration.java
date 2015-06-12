/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        // From example, no idea how this actually works
        /*        auth.ldapAuthentication()
         * .userDnPatterns("uid={0},ou=people")
         * .groupSearchBase("ou=groups")
         * .contextSource().ldif("classpath:test-server.ldif");*/

        // Test Users
        auth.inMemoryAuthentication()
                .withUser("viewer").password("viewer").roles("VIEWER");
        auth.inMemoryAuthentication()
                .withUser("moderator").password("moderator").roles("MODERATOR");
        auth.inMemoryAuthentication()
                .withUser("both").password("both").roles("MOREDATORs", "VIEWER");
    }
}
