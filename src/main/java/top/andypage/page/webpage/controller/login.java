package top.andypage.page.webpage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.provider.tokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller
public class login {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private tokenProvider newToken;

    @GetMapping("/login")
    public String login(){
        return "login3";
    }
    //"loginControl"

    @PostMapping(value="/signin")
    public String signin(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpServletRequest request,
                         HttpServletResponse response){
        System.out.println(username+password);
        if(!username.isEmpty()&&!password.isEmpty()){

            User user=userMapper.findByUser(username);
            String token=newToken.makeToken();

            if(user!=null){
                if(user.getPassword().equals(password)){

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("username", username);
                    request.getSession().setAttribute("avatar", user.getAvatar());
                    String m_time=dateformat.format(System.currentTimeMillis());
                    request.getSession().setAttribute("modified_time", m_time);
                    String c_time=dateformat.format(user.getCreate_time());
                    request.getSession().setAttribute("create_time", c_time);
                    System.out.println(c_time+m_time);
                    request.getSession().setAttribute("wrongInfo", null);
                    userMapper.updateToken(token,username);
                    Cookie cookie=new Cookie("tokenForAndy",token);
                    cookie.setMaxAge(30 * 60);
                    response.addCookie(cookie);

                    return "redirect:/";
                }else{
                    request.getSession().setAttribute("wrongInfo", "sthwrong");
                }
            }
            request.getSession().setAttribute("wrongInfo", "sthwrong");
            return "redirect:login";
        }else{
            return "redirect:login";
        }


    }


    @GetMapping(value="/logout")
    public String logout(@CookieValue("tokenForAndy") String token,
            HttpServletRequest request,
            HttpServletResponse response){

                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", null);
                    request.getSession().setAttribute("username", null);
                    userMapper.deleteToken(user.getUsername());

                }


        Cookie cookie = new Cookie("tokenForAndy", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }
}
