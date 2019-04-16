package com.vbank.sso.services;

import java.security.Principal;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserHandlerService implements
		HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
		ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory) throws Exception {
        if (this.supportsParameter(methodParameter)) {
            Principal principal = (Principal) webRequest.getUserPrincipal();
            Authentication au = (Authentication)principal;
            User u = null;
            if(au != null) {
                u = (User) au.getPrincipal();
            }
            return u;
	} else {
	    return WebArgumentResolver.UNRESOLVED;
	}
    }
}