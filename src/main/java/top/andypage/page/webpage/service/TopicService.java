package top.andypage.page.webpage.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.dataTransferObject.PageDTO;
import top.andypage.page.webpage.dataTransferObject.topicDTO;
import top.andypage.page.webpage.model.Topic;
import top.andypage.page.webpage.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;


    public PageDTO listPage(Integer page,Integer size,Integer startIndex) {

        List<Topic> topics=topicMapper.listPage(startIndex,size);
        Integer fullSize=topicMapper.count();
        List<topicDTO> topicDTOS=new ArrayList<>();
        List<Integer> topicPages=new ArrayList<>();

        Integer fullPage=fullSize%size==0?fullSize/size:fullSize/size+1;


        for(Topic topic:topics){
            User user=userMapper.findById(topic.getPublisher_id());
            topicDTO topicDto=new topicDTO();
            BeanUtils.copyProperties(topic,topicDto);
            topicDto.setUser(user);
            topicDTOS.add(topicDto);
        }

        if(page<=2){
            Integer wholePage=Math.min(5,fullPage);
            for(int i = 0;i<wholePage;i++){
                topicPages.add(i+1);
            }
        }else if(page>=fullPage-2){
            Integer wholePage=Math.max(1,fullPage-4);
            for(int i=fullPage;i>=wholePage;i--){
                topicPages.add(fullPage-i+2);
            }
        }else{
            topicPages.add(page-2);
            topicPages.add(page-1);
            topicPages.add(page);
            topicPages.add(page+1);
            topicPages.add(page+2);
        }

        PageDTO pageDTO=new PageDTO();
        pageDTO.setCurrentPage(page);
        pageDTO.setPageShow(topicPages);
        pageDTO.setTopic(topicDTOS);
        pageDTO.setFinalPageIndex(fullPage);

        if(page==1){
            pageDTO.setPrevButton(false);
        }
        if(page==fullPage){
            pageDTO.setNextButton(false);
        }
        if(topicPages.contains(1)){
            pageDTO.setFirstButton(false);
        }
        if(topicPages.contains(fullPage)){
            pageDTO.setFinalButton(false);
        }
        System.out.println(page);
        System.out.println(fullPage);

        return pageDTO;
    }

    public topicDTO topicInfo(Integer id){
        topicDTO topicDto=new topicDTO();
        Topic topic=topicMapper.getTopicDto(id);

        User user=userMapper.findById(topic.getPublisher_id());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateformat.format(user.getCreate_time());
        System.out.println(time);
        topic.setC_time(time);
        BeanUtils.copyProperties(topic,topicDto);

        topicDto.setUser(user);

        return topicDto;
    }

    public PageDTO profilePage(Integer userId,Integer page,Integer size){
        Integer startIndex=(page-1)*size;
        List<Topic> topics=topicMapper.getTopicByPublisher(userId,startIndex,size);
        Integer fullSize=topicMapper.count();
        List<topicDTO> topicDTOS=new ArrayList<>();
        List<Integer> topicPages=new ArrayList<>();

        Integer fullPage=fullSize%size==0?fullSize/size:fullSize/size+1;


        for(Topic topic:topics){
            User user=userMapper.findById(topic.getPublisher_id());
            user.setAvatar("/"+user.getAvatar());
            topicDTO topicDto=new topicDTO();
            BeanUtils.copyProperties(topic,topicDto);
            topicDto.setUser(user);
            topicDTOS.add(topicDto);
        }
        System.out.println(topicDTOS);

        if(page<=2){
            Integer wholePage=Math.min(5,fullPage);
            for(int i = 0;i<wholePage;i++){
                topicPages.add(i+1);
            }
        }else if(page>=fullPage-2){
            Integer wholePage=Math.max(1,fullPage-4);
            for(int i=fullPage;i>=wholePage;i--){
                topicPages.add(fullPage-i+1);
            }
        }else{
            Integer wholePageMax=Math.min(5,fullPage);
            Integer wholePageMin=Math.max(1,fullPage-4);
            for(int i =page-2;i<=page+2;i++){
                if(i>=wholePageMin&&i<=wholePageMax){
                    topicPages.add(i);
                }
            }
        }

        PageDTO pageDTO=new PageDTO();
        pageDTO.setCurrentPage(page);
        pageDTO.setPageShow(topicPages);
        pageDTO.setTopic(topicDTOS);
        pageDTO.setFinalPageIndex(fullPage);

        if(page==1){
            pageDTO.setPrevButton(false);
        }
        if(page==fullPage){
            pageDTO.setNextButton(false);
        }
        if(topicPages.contains(1)){
            pageDTO.setFirstButton(false);
        }
        if(topicPages.contains(fullPage)){
            pageDTO.setFinalButton(false);
        }


        return pageDTO;
    };

}
