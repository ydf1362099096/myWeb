package top.andypage.page.webpage.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.andypage.page.webpage.Exception.CustomErrorCode;
import top.andypage.page.webpage.Exception.CustomException;
import top.andypage.page.webpage.Mapper.CommentMapper;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.Mapper.UserMapper;
import top.andypage.page.webpage.dataTransferObject.CommentDTO;
import top.andypage.page.webpage.dataTransferObject.TopicCommentDTO;
import top.andypage.page.webpage.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;

    public int insert(Comment comment) {
        Topic topic=topicMapper.selectByPrimaryKey(comment.getParentid());
        if(comment.getParentid()==null||comment.getParentid()==0||topic==null){
            System.out.println("REPLY_TARGET_NOT_EXIST");
            throw new CustomException(CustomErrorCode.REPLY_TARGET_NOT_EXIST);
        }

        if(comment.getContent()==null||comment.getContent()==""){
            System.out.println("REPLY_CONTENT_NULL");
            throw new CustomException(CustomErrorCode.REPLY_CONTENT_NULL);
        }

        System.out.println("kaishicharu");
        int result=commentMapper.insert(comment);
        if(result!=0){
            topic.setCommentCount(topic.getCommentCount()+1);
            topicMapper.updateByPrimaryKey(topic);
        }
        System.out.println("charuwancheng");
        return 1;
    }

    public TopicCommentDTO listPage(Long TopicId, Integer page, Integer size) {

        Integer startIndex=(page-1)*size;
        RowBounds rowBounds=new RowBounds(startIndex,size);
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria().andParentidEqualTo(TopicId);
        List<Comment> comments=commentMapper.selectByExampleWithBLOBsWithRowbounds(commentExample,rowBounds);

        if(comments.size()==0){
            return null;
        }

        Integer fullSize=(int)commentMapper.countByExample(commentExample);
        List<CommentDTO> commentDTOS=new ArrayList<>();
        List<Integer> commentPages=new ArrayList<>();

        Integer fullPage=fullSize%size==0?fullSize/size:fullSize/size+1;


        for(Comment comment:comments){
            Topic topic=topicMapper.selectByPrimaryKey(comment.getParentid());
            User user=userMapper.selectByPrimaryKey(comment.getCommentorid());
            CommentDTO commentDTO=new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUesr(user);
            commentDTOS.add(commentDTO);
        }

        if(page<=2){
            Integer wholePage=Math.min(5,fullPage);
            for(int i = 0;i<wholePage;i++){
                commentPages.add(i+1);
            }
        }else if(page>=fullPage-2){
            Integer wholePage=Math.max(1,fullPage-4);
            for(int i=fullPage;i>=wholePage;i--){
                commentPages.add(fullPage-i+1);
            }
        }else{
            Integer wholePageMax=Math.min(5,fullPage);
            Integer wholePageMin=Math.max(1,fullPage-4);
            for(int i =page-2;i<=page+2;i++){
                if(i>=wholePageMin&&i<=wholePageMax){
                    commentPages.add(i);
                }
            }
        }

        TopicCommentDTO topicCommentDTO=new TopicCommentDTO();
        topicCommentDTO.setCurrentPage(page);
        topicCommentDTO.setPageShow(commentPages);
        topicCommentDTO.setComment(commentDTOS);
        topicCommentDTO.setFinalPageIndex(fullPage);

        if(page==1){
            topicCommentDTO.setPrevButton(false);
        }
        if(page==fullPage){
            topicCommentDTO.setNextButton(false);
        }
        if(commentPages.contains(1)){
            topicCommentDTO.setFirstButton(false);
        }
        if(commentPages.contains(fullPage)){
            topicCommentDTO.setFinalButton(false);
        }


        return topicCommentDTO;
    }
}


