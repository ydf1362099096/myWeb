package top.andypage.page.webpage.helloController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.provider.tokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("username", username);
                    request.getSession().setAttribute("wrongInfo", null);
                    userMapper.updateToken(token,username);
                    response.addCookie(new Cookie("token",token));
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

    @PostMapping(value="/signup")
    public String signup(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpServletRequest request,
                               HttpServletResponse response){
        System.out.println(username+password);
        if(!username.isEmpty()&&!password.isEmpty()){
            String token=newToken.makeToken();
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setCreate_time(System.currentTimeMillis());
            user.setModified_time(user.getCreate_time());
            user.setToken(token);
            userMapper.insert(user);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("username", username);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            return "login";
        }

    }
    @PostMapping(value="/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response){


        return "redirect:/";
    }
}
