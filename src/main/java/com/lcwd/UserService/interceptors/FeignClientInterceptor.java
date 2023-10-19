package com.lcwd.UserService.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("All")
public class FeignClientInterceptor implements RequestInterceptor {

    /*
     * This interceptor will add the authorization header set with Oauth2 token
     * before sending the request so that user service can access rating and
     * hotel services which are secured with oauth2 authorization
     */

    @Autowired
    OAuth2AuthorizedClientManager clientManager;
    @Override
    public void apply(RequestTemplate requestTemplate) {

        String token = clientManager.authorize(OAuth2AuthorizeRequest
                                    .withClientRegistrationId("internal-client")
                                    .principal("internal")
                                    .build())
                                    .getAccessToken()
                                    .getTokenValue();

        requestTemplate.header("Authorization", "Bearer "+token);
    }
}
