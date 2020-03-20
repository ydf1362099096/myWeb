package top.andypage.page.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.dataTransferObject.topicDTO;
import top.andypage.page.webpage.service.TopicService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TopicShow {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicService topicService;

    @GetMapping("/topicShow")
    public String topicShow(Model model,
                        @RequestParam(value = "topicId") Integer topicId){


        topicDTO topicDto=topicService.topicInfo(topicId);

        model.addAttribute("topicDTO",topicDto);

        return "topicShow";
    }

}
