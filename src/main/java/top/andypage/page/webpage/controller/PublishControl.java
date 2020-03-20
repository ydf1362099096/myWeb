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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishControl {
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
                         HttpServletRequest request,
                         HttpServletResponse response,
                          Model model){
        model.addAttribute("publishWrong",null);
        Cookie cok[]=request.getCookies();
        for(Cookie cookie:cok){
            if(cookie.getName().equals("tokenForAndy")){
                String token=cookie.getValue();
                User user=userMapper.findByToken(token);
                if(user!=null) {
                    Topic topic=new Topic();
                    topic.setTitle(topicInput);
                    topic.setDescription(description);
                    topic.setCreate_time(System.currentTimeMillis());
                    topic.setModified_time(topic.getCreate_time());
                    topic.setPublisher_id(user.getId());
                    topic.setComment_count(0);
                    topic.setLike_count(0);
                    topic.setView_count(0);
                    topicMapper.insertTopic(topic);
                    return "redirect:/";
                }
            }
        }
        System.out.println("Chucuo");
        model.addAttribute("publishWrong","please Login first!");
        return "publish";
    }
}
