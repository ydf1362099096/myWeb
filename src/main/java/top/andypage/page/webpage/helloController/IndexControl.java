package top.andypage.page.webpage.helloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexControl {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String hello(HttpServletRequest request){
        Cookie cok[]=request.getCookies();
        for(Cookie cookie:cok){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user=userMapper.findByToken(token);
                if(user!=null) {
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("username", user.getUsername());
                    break;
                }
            }
        }

        return "index";
    }
}
