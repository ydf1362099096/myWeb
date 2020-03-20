package top.andypage.page.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.dataTransferObject.PageDTO;
import top.andypage.page.webpage.dataTransferObject.topicDTO;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.service.TopicService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
public class IndexControl {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer pageIndex,
                        @RequestParam(value = "topicCount",defaultValue = "3") Integer topicNum){


        model.addAttribute("username",request.getSession().getAttribute("Username"));
        Integer startIndex=(pageIndex-1)*topicNum;
        PageDTO pageDTO=topicService.listPage(pageIndex,topicNum,startIndex);
        model.addAttribute("pageDTO", pageDTO);

        return "index";
    }


}
