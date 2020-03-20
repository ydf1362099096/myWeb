package top.andypage.page.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.provider.tokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller
public class SignUp {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private tokenProvider newToken;

    @GetMapping("sign_up")
    public String sign_up(){
        return "sign_up";
    }

    @PostMapping(value="/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpServletRequest request,
                         HttpServletResponse response){
        if(!username.isEmpty()&&!password.isEmpty()){

            User user=userMapper.findByUser(username);


            if(user==null){

                String token=newToken.makeToken();
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                User newUser=new User();

                newUser.setUsername(username);
                newUser.setPassword(password);
                long time=System.currentTimeMillis();
                newUser.setCreate_time(time);
                newUser.setModified_time(time);
                userMapper.insert(newUser);

                request.getSession().setAttribute("user", newUser);
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("avatar", "avatarImg/default.jpeg");
                String m_time=dateformat.format(System.currentTimeMillis());
                request.getSession().setAttribute("modified_time", m_time);
                String c_time=dateformat.format(newUser.getCreate_time());
                request.getSession().setAttribute("create_time", c_time);
                request.getSession().setAttribute("wrongInfo", null);

                Cookie cookie=new Cookie("tokenForAndy",token);
                cookie.setMaxAge(30 * 60);
                    response.addCookie(cookie);
                    return "redirect:/";
            } else{
                request.getSession().setAttribute("wrongInfo", "用户名已存在");
            }

        }else{
            request.getSession().setAttribute("wrongInfo", "用户名或密码不能为空");
        }

        return "sign_up";
}

}
