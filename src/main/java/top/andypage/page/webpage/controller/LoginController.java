package top.andypage.page.webpage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.dataTransferObject.LoginDTO;
import top.andypage.page.webpage.dataTransferObject.LoginResultDTO;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.model.UserExample;
import top.andypage.page.webpage.provider.tokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class LoginController {

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


        if(!username.isEmpty()&&!password.isEmpty()){
            UserExample userExample=new UserExample();
            userExample.createCriteria().andUsernameEqualTo(username);
            List<User> users= userMapper.selectByExample(userExample);
            System.out.println(users.get(0).getUsername());
            if (users.size() != 0) {
                User user=users.get(0);
                System.out.println(user.getUsername());
                if(user.getPassword().equals(password)){
                    String token=newToken.makeToken();
                    UserExample newExample=new UserExample();
                    newExample.createCriteria().andTokenEqualTo(token);
                    newExample.createCriteria().andModifiedTimeEqualTo(System.currentTimeMillis());
                    user.setToken(token);
                    user.setModifiedTime(System.currentTimeMillis());
                    int avc=userMapper.updateByPrimaryKey(user);
                    System.out.println("charu"+avc);

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("username", username);
                    request.getSession().setAttribute("avatar", user.getAvatar());
                    String m_time=dateformat.format(System.currentTimeMillis());
                    request.getSession().setAttribute("modified_time", m_time);
                    String c_time=dateformat.format(user.getCreateTime());
                    request.getSession().setAttribute("create_time", c_time);
                    request.getSession().setAttribute("wrongInfo", null);
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

    @ResponseBody
    @RequestMapping(value= "/signinWithTopic", method = RequestMethod.POST)
    public Object post(@RequestBody LoginDTO loginDTO,
                       HttpServletRequest request,
                       HttpServletResponse response){
        System.out.println("receive request");
        System.out.println(loginDTO.getPassword());
        System.out.println(loginDTO.getUsername());
        String username=loginDTO.getUsername();
        String password=loginDTO.getPassword();

        if(!username.isEmpty()&&!password.isEmpty()){
            UserExample userExample=new UserExample();
            userExample.createCriteria().andUsernameEqualTo(username);
            List<User> users= userMapper.selectByExample(userExample);
            System.out.println(users.get(0).getUsername());
            if (users.size() != 0) {
                User user=users.get(0);
                System.out.println(user.getUsername());
                if(user.getPassword().equals(password)){
                    String token=newToken.makeToken();
                    UserExample newExample=new UserExample();
                    newExample.createCriteria().andTokenEqualTo(token);
                    newExample.createCriteria().andModifiedTimeEqualTo(System.currentTimeMillis());
                    user.setToken(token);
                    user.setModifiedTime(System.currentTimeMillis());
                    int avc=userMapper.updateByPrimaryKey(user);
                    System.out.println("charu"+avc);

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("username", username);
                    request.getSession().setAttribute("avatar", user.getAvatar());
                    String m_time=dateformat.format(System.currentTimeMillis());
                    request.getSession().setAttribute("modified_time", m_time);
                    String c_time=dateformat.format(user.getCreateTime());
                    request.getSession().setAttribute("create_time", c_time);
                    request.getSession().setAttribute("wrongInfo", null);
                    Cookie cookie=new Cookie("tokenForAndy",token);
                    cookie.setMaxAge(30 * 60);
                    response.addCookie(cookie);

                    return LoginResultDTO.okStatus();
                }else{
                    return LoginResultDTO.passwordWrong();
                }
            }
            return LoginResultDTO.usernameWrong();
        }else{
            return LoginResultDTO.empty();
        }

    }



    @GetMapping(value="/logout")
    public String logout(@CookieValue("tokenForAndy") String token,
            HttpServletRequest request,
            HttpServletResponse response){
            System.out.println("logout called");
                UserExample userExample=new UserExample();
                userExample.createCriteria().andTokenEqualTo(token);
                List<User> users= userMapper.selectByExample(userExample);

                if (users.size() != 0) {
                    User user=users.get(0);
                    UserExample tempUserExample=new UserExample();
                    tempUserExample.createCriteria().andUsernameEqualTo(token);
                    User tempUser=new User();
                    tempUser.setToken("");
                    user.setToken("");

                    userMapper.updateByPrimaryKey(user);
                    //userMapper.updateByExample(tempUser,tempUserExample);
                }

        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("username", null);
        Cookie cookie = new Cookie("tokenForAndy", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }
}
