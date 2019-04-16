package com.vbank.sso;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Application extends SpringBootServletInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    @RequestMapping("/securePage")
    public String securePage(User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            LOG.error("Authentication service is offline!");
        }
        model.addAttribute("username", user.getUsername());
        return ("/securePage.html");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        LOG.info("SAML Demo service started sucessfully");
    }
	
}