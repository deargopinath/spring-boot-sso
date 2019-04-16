package com.vbank.sso.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements SAMLUserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsService.class);
    @Override
    public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
        String userID = credential.getNameID().getValue();
        LOG.info(userID + " is logged in");
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);
        return new User(userID, "No password required", true, true, true, true, authorities);
    }	
}
