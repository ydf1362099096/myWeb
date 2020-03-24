package top.andypage.page.webpage.configPkg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.logging.Handler;

@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cok[]=request.getCookies();
        if(cok!=null&&cok.length!=0) {
            System.out.println("you cookie");
            for (Cookie cookie : cok) {
                if (cookie.getName().equals("tokenForAndy")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String m_time=dateformat.format(user.getModified_time());
                        String c_time=dateformat.format(user.getCreate_time());
                        request.getSession().setAttribute("user", user);
                        request.getSession().setAttribute("username", user.getUsername());
                        request.getSession().setAttribute("modified_time", m_time);
                        request.getSession().setAttribute("create_time", c_time);
                        request.getSession().setAttribute("avatar", user.getAvatar());
                        break;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
