package top.andypage.page.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.andypage.page.webpage.Exception.CustomErrorCode;
import top.andypage.page.webpage.Exception.CustomException;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.dataTransferObject.*;
import top.andypage.page.webpage.model.Topic;
import top.andypage.page.webpage.model.TopicExample;
import top.andypage.page.webpage.model.User;
import top.andypage.page.webpage.service.CommentService;
import top.andypage.page.webpage.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TopicController {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicService topicService;
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value= "/viewCount", method = RequestMethod.POST)
    public Object viewCount(@RequestBody ViewCountDTO viewCountDTO,
                           HttpServletRequest request){
        System.out.println("dianzan"+viewCountDTO.getId());
        Topic topic=topicMapper.selectByPrimaryKey(viewCountDTO.getId());
        topic.setViewCount(topic.getViewCount()+1);
        topicMapper.updateByPrimaryKey(topic);

        System.out.println("dianzancgengong");
        return CommentResultDTO.okStatus();
    }

    @GetMapping("/topicShow/{topicId}")
    public String topicShow(Model model,
                            HttpServletRequest request,
                            @PathVariable(value = "topicId") Long topicId,
                            @RequestParam(name="page",defaultValue = "1") Integer CommentIndex,
                            @RequestParam(name="size",defaultValue = "10") Integer size){


        Topic topic = topicMapper.selectByPrimaryKey(topicId);
        if(topic==null){
            throw new CustomException(CustomErrorCode.TOPIC_NOT_FOUND);
        }

        if(request.getSession().getAttribute("user")!=null){
            User user=(User) request.getSession().getAttribute("user");
            Long id=user.getId();
            User thisUser=userMapper.selectByPrimaryKey(id);
            String viewRec=thisUser.getViewrecord();
            List<String> a = Arrays.asList(viewRec.split("@"));
            String newRecord="";
            if(viewRec!=""){
                if(a.contains(String.valueOf(topicId))){
                    if(a.size()<=9){
                        for(int i=0;i<a.size();i++){
                            if(a.get(i)!=String.valueOf(topicId)) {
                                newRecord = newRecord + '@' + a.get(i);
                            }
                        }
                        newRecord=(topicId)+newRecord;
                    }else{
                        for(int i=0;i<9;i++){
                            if(a.get(i)!=String.valueOf(topicId)){
                                newRecord=newRecord+'@'+a.get(i);
                            }
                        }
                        newRecord=(topicId)+newRecord;
                    }
                }else{
                    if(a.size()<=9){
                        for(int i=0;i<a.size();i++){
                            newRecord=newRecord+'@'+a.get(i);
                        }
                        newRecord=(topicId)+newRecord;
                    }else{
                        for(int i=0;i<9;i++){
                            newRecord=newRecord+'@'+a.get(i);
                        }
                        newRecord=(topicId)+newRecord;
                    }
                }

            }else{
                newRecord=String.valueOf(topicId);
            }
            thisUser.setViewrecord(newRecord);
            userMapper.updateByPrimaryKey(thisUser);
        }

        TopicCommentDTO topicCommentDTO=new TopicCommentDTO();
        topicCommentDTO=commentService.listPage(topicId,CommentIndex,size);

        topicDTO topicDto=topicService.topicInfo(topicId);
        if(request.getSession().getAttribute("userId")==topic.getPublisherId()){
            topic.setLikeCount(0);
            topicMapper.updateByPrimaryKey(topic);
        }
        List<Topic> relatedTopics=new ArrayList<>();
        if(topic.getTag()!=null||topic.getTag()!=""){
            List<String> newTags=Arrays.asList(topic.getTag().split(","));
            topicDto.setTagList(newTags);
            for(String s:newTags){
                TopicExample example=new TopicExample();
                example.createCriteria().andTagLike("%"+s+"%");
                List<Topic> relatedTopic=topicMapper.selectByExample(example);
                for(Topic tempTopic:relatedTopic) {

                    if (tempTopic.getId() != topicId) {
                        relatedTopics.add(tempTopic);
                    }
                    if(relatedTopics.size()>=5) break;
                }
            }
            topicDto.setRelatedTopics(relatedTopics);
        }



        model.addAttribute("topicDTO",topicDto);
        if(topicCommentDTO!=null){
            model.addAttribute("comments",topicCommentDTO);
        }else{
            model.addAttribute("comments",null);
        }


        return "topicShow";
    }

}
