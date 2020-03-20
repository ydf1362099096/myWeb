package top.andypage.page.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.dataTransferObject.PageDTO;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.service.TopicService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileControl {

    @Autowired
    private TopicService topicService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action",value = "")String action,
                          @RequestParam(name="page",defaultValue = "1") Integer pageIndex,
                          @RequestParam(name="size",defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model){

        Object user2=request.getSession().getAttribute("user");
        if(user2==null){
            return "/login3";
        }
        User user=(User)user2;
        System.out.println("ID is "+user.getId());


        PageDTO pageDTO=topicService.profilePage(user.getId(),pageIndex,size);
        model.addAttribute("pageDTO", pageDTO);
        System.out.println("ID is "+pageDTO.getPageShow());

        if(action.equals("myTopic")){
            model.addAttribute("section","我的话题");
            model.addAttribute("pageDTO", pageDTO);
        }

        return "profile";
    }

}
