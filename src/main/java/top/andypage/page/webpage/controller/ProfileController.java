package top.andypage.page.webpage.controller;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.Exception.CustomErrorCode;
import top.andypage.page.webpage.Exception.CustomException;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.dataTransferObject.PageDTO;
import top.andypage.page.webpage.dataTransferObject.topicDTO;
import top.andypage.page.webpage.model.Topic;
import top.andypage.page.webpage.model.TopicExample;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action",value = "")String action,
                          @RequestParam(name="page",defaultValue = "1") Integer pageIndex,
                          @RequestParam(name="size",defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model){

        Object tempUser=request.getSession().getAttribute("user");
        if(tempUser==null){
            return "/login3";
        }
        User user=(User)tempUser;
        System.out.println("ID is "+user.getId());

        int reply=0;
        List<topicDTO> topicDTOS=topicService.myReply(user.getId());

        for(topicDTO t:topicDTOS){
            reply+=t.getLikeCount();
        }

        model.addAttribute("newReply",reply);


        if(action.equals("myTopic")){
            PageDTO pageDTO=topicService.profilePage(user.getId(),pageIndex,size);
            model.addAttribute("section","我的话题");
            model.addAttribute("pageDTO", pageDTO);
            model.addAttribute("action", "myTopic");
            return "profile";
        } else if(action.equals("myViewRecord")){
            PageDTO pageDTO=topicService.myViewRec(user);
            model.addAttribute("section","我的浏览记录");
            model.addAttribute("pageDTO", pageDTO);
            model.addAttribute("action", "myViewRecord");
            return "profile_view_record";
        }else if(action.equals("myReply")){
            PageDTO pageDTO=new PageDTO();
            pageDTO.setTopic(topicDTOS);
            model.addAttribute("section","我的新回复");
            model.addAttribute("pageDTO", pageDTO);
            model.addAttribute("action", "myReply");
            return "profile_reply";
        }else{
            throw new CustomException(CustomErrorCode.PAGE_NOT_EXIST);
        }


    }

}
