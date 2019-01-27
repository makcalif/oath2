package com.spark.arabic.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import java.util.Collections;

@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfig  {

    @Value("${spring.cloud.config.auth.uri}")
    private String uri;

    @Value("${spring.cloud.config.auth.client-id}")
    private String clientId;

    @Value("${spring.cloud.config.auth.secret}")
    private String secret;

    @Value("${spring.cloud.config.auth.grant-type}")
    private String grantType;

    @Value("${spring.cloud.config.auth.scope}")
    private String scope;

    @Autowired
    private Environment environment;

//    @Bean
//    public ConfigClientProperties configClientProperties() {
//        ConfigClientProperties client = new ConfigClientProperties(this.environment);
//        client.setEnabled(Boolean.FALSE);
//        client.getDiscovery().setEnabled(Boolean.TRUE);
//        return client;
//    }

//    @Bean
//    public ConfigServicePropertySourceLocator configServicePropertySourceLocator() {
//        ConfigClientProperties clientProperties = configClientProperties();
//        ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(clientProperties);
//        configServicePropertySourceLocator.setRestTemplate(oauth2RestTemplate());
//        return configServicePropertySourceLocator;
//    }

    private OAuth2RestTemplate oauth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(uri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(secret);
        resourceDetails.setGrantType(grantType);
        resourceDetails.setScope(Collections.singletonList(scope));
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        return new OAuth2RestTemplate(resourceDetails, clientContext);
    }
}

