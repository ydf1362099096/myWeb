package top.andypage.page.webpage.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.dataTransferObject.PageDTO;
import top.andypage.page.webpage.dataTransferObject.topicDTO;
import top.andypage.page.webpage.model.Topic;
import top.andypage.page.webpage.model.TopicExample;
import top.andypage.page.webpage.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;


    public PageDTO listPage(Integer page, Integer size, Integer startIndex) {

        RowBounds rowBounds=new RowBounds(startIndex,size);
        TopicExample topicExample=new TopicExample();
        TopicExample.Criteria criteria= topicExample.createCriteria();
        topicExample.setOrderByClause("CREATE_TIME DESC");
        List<Topic> topics=topicMapper.selectByExampleWithBLOBsWithRowbounds(topicExample,rowBounds);
        Integer fullSize=(int)topicMapper.countByExample(new TopicExample());
        List<topicDTO> topicDTOS=new ArrayList<>();
        List<Integer> topicPages=new ArrayList<>();

        Integer fullPage=fullSize%size==0?fullSize/size:fullSize/size+1;


        for(Topic topic:topics){
            User user=userMapper.selectByPrimaryKey(topic.getPublisherId());
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


        return pageDTO;
    }

    public PageDTO listHotestPage(Integer page, Integer size, Integer startIndex) {

        RowBounds rowBounds=new RowBounds(startIndex,size);
        TopicExample topicExample=new TopicExample();
        TopicExample.Criteria criteria= topicExample.createCriteria();
        topicExample.setOrderByClause("VIEW_COUNT DESC,CREATE_TIME DESC");
        List<Topic> topics=topicMapper.selectByExampleWithBLOBsWithRowbounds(topicExample,rowBounds);
        Integer fullSize=(int)topicMapper.countByExample(new TopicExample());
        List<topicDTO> topicDTOS=new ArrayList<>();
        List<Integer> topicPages=new ArrayList<>();

        Integer fullPage=fullSize%size==0?fullSize/size:fullSize/size+1;

        Integer replyCount=0;
        for(Topic topic:topics){
            User user=userMapper.selectByPrimaryKey(topic.getPublisherId());
            topicDTO topicDto=new topicDTO();
            BeanUtils.copyProperties(topic,topicDto);
            topicDto.setUser(user);
            topicDTOS.add(topicDto);
            replyCount+=topic.getLikeCount();
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
        pageDTO.setNewReplyCount(replyCount);

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
    }

    public topicDTO topicInfo(Long id){
        topicDTO topicDto=new topicDTO();

        Topic topic=topicMapper.selectByPrimaryKey(id);
        User user=userMapper.selectByPrimaryKey(topic.getPublisherId());

        BeanUtils.copyProperties(topic,topicDto);
        topicDto.setUser(user);

        return topicDto;
    }

    public PageDTO myViewRec(User user){
        List<topicDTO> myTopic=new ArrayList();
        String viewRec=user.getViewrecord();
        String[] IDList=viewRec.split("@");
        PageDTO pageDTO=new PageDTO();
        if(viewRec!=""){
            for(int i=0;i<IDList.length;i++){
                Long id= Long.valueOf(IDList[i]);
                Topic topic=topicMapper.selectByPrimaryKey(id);
                User user3=userMapper.selectByPrimaryKey(topic.getPublisherId());
                topicDTO topicDto=new topicDTO();
                BeanUtils.copyProperties(topic,topicDto);
                topicDto.setUser(user3);
                myTopic.add(topicDto);
            }

            pageDTO.setTopic(myTopic);
        }else{

        }

        return pageDTO;
    }

    public PageDTO profilePage(Long userId,Integer page,Integer size){
        Integer startIndex=(page-1)*size;
        RowBounds rowBounds=new RowBounds(startIndex,size);
        TopicExample topicExample=new TopicExample();
        topicExample.createCriteria().andPublisherIdEqualTo(userId);
        topicExample.setOrderByClause("'createTime' DESC");
        List<Topic> topics=topicMapper.selectByExampleWithBLOBsWithRowbounds(topicExample,rowBounds);

        Integer fullSize=(int)topicMapper.countByExample(topicExample);
        List<topicDTO> topicDTOS=new ArrayList<>();
        List<Integer> topicPages=new ArrayList<>();

        Integer fullPage=fullSize%size==0?fullSize/size:fullSize/size+1;


        for(Topic topic:topics){
            User user=userMapper.selectByPrimaryKey(topic.getPublisherId());
            user.setAvatar("/"+user.getAvatar());
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

    public List<topicDTO> myReply(Long id) {
        User user=userMapper.selectByPrimaryKey(id);
        TopicExample topicExample=new TopicExample();
        topicExample.createCriteria().andPublisherIdEqualTo(id);
        List<Topic> topics=topicMapper.selectByExample(topicExample);
        List<topicDTO> topicDTOS=new ArrayList<>();

        for(Topic topic:topics){
            if(topic.getLikeCount()!=0){
                topicDTO topicDto=new topicDTO();
                BeanUtils.copyProperties(topic,topicDto);
                topicDto.setUser(user);
                topicDTOS.add(topicDto);
            }
        }

        return topicDTOS;
    }

}
