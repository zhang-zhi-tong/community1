package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubPorvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubPorvider githubPorvider;
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("7089ccc3173b2898da8f");
        accessTokenDto.setClient_secret("4f636efc01f649a7e85c165aafb87b7c06a07a3a");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        String token =  githubPorvider.getAccessToken(accessTokenDto);
        GithubUser user = githubPorvider.getUser(token);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getBio());
        return "index";
    }
}
