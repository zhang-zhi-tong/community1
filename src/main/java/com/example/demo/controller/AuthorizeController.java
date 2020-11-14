package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubPorvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubPorvider githubPorvider;
    //通过Value注解将application.properties中的属性引入到这里
    @Value("${github.client.id}")
    private  String clientId;
    @Value("${github.client.secret}")
    private  String clientSecret;
    @Value("${github.redirect.uri}")
    private  String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String token =  githubPorvider.getAccessToken(accessTokenDto);
        GithubUser user = githubPorvider.getUser(token);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getBio());
        return "index";
    }
}
