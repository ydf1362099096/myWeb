package top.andypage.page.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.model.Topic;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.model.UserExample;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicMapper topicMapper;


    @GetMapping("/publish")
    public String returnPublish(){
        return "publish";
    }

    @PostMapping(value="/publish")
    public String publish(@RequestParam("topicInput") String topicInput,
                         @RequestParam("description") String description,
                          @RequestParam(value = "typeSelect",defaultValue = "无") String typeSelect,
                         HttpServletRequest request,
                         HttpServletResponse response,
                          Model model){
        model.addAttribute("publishWrong",null);
        System.out.println(typeSelect);
        Cookie cok[]=request.getCookies();
        for(Cookie cookie:cok){
            if(cookie.getName().equals("tokenForAndy")){
                String token=cookie.getValue();
                UserExample userExample=new UserExample();
                userExample.createCriteria().andTokenEqualTo(token);
                List<User> users=userMapper.selectByExample(userExample);
                System.out.println(users.size());
                if(users.size()!=0) {
                    User user=users.get(0);
                    Topic topic=new Topic();
                    topic.setTitle(topicInput);
                    topic.setDescription(description);
                    topic.setCreateTime(System.currentTimeMillis());
                    topic.setModifiedTime(topic.getCreateTime());
                    topic.setTag(typeSelect);
                    topic.setPublisherId(user.getId());
                    topic.setCommentCount(0);
                    topic.setLikeCount(0);
                    topic.setViewCount(0);
                    topicMapper.insert(topic);

                    return "redirect:/";
                }
            }
        }
        System.out.println("Chucuo");
        model.addAttribute("publishWrong","please Login first!");
        return "publish";
    }
}
