package top.andypage.page.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.andypage.page.webpage.Exception.CustomErrorCode;
import top.andypage.page.webpage.Exception.CustomException;
import top.andypage.page.webpage.Mapper.CommentMapper;
import top.andypage.page.webpage.Mapper.TopicMapper;
import top.andypage.page.webpage.dataTransferObject.CommentResultDTO;
import top.andypage.page.webpage.dataTransferObject.InputCommentDTO;
import top.andypage.page.webpage.dataTransferObject.LikeVoteDTO;
import top.andypage.page.webpage.model.*;
import top.andypage.page.webpage.service.CommentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TopicMapper topicMapper;

    @ResponseBody
    @RequestMapping(value= "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody InputCommentDTO inputCommentDTO,
                       HttpServletRequest request){
        System.out.println("receive request");
        System.out.println(inputCommentDTO.getContent());
        if(inputCommentDTO.getContent()==""){
            throw new CustomException(CustomErrorCode.REPLY_CONTENT_NULL);
        }
        User user=(User)request.getSession().getAttribute("user");
        //User user=userMapper.selectByPrimaryKey((long)9);
        if(user==null){
            System.out.println("yonghuweidenglu");
            throw new CustomException(CustomErrorCode.USER_NOT_LOGIN);
        }

        Topic topic=topicMapper.selectByPrimaryKey(inputCommentDTO.getParentid());
        topic.setLikeCount(topic.getLikeCount()+1);
        topicMapper.updateByPrimaryKey(topic);

        Comment comment=new Comment();
        comment.setParentid(inputCommentDTO.getParentid());
        comment.setContent(inputCommentDTO.getContent());
        comment.setType(inputCommentDTO.getType());
        comment.setLikecount(0);
        comment.setCommenttime(System.currentTimeMillis());
        comment.setCommentorid(user.getId());

        int result=commentService.insert(comment);

        System.out.println("jieshu");
        return CommentResultDTO.okStatus();
    }

    @ResponseBody
    @RequestMapping(value= "/likeVote", method = RequestMethod.POST)
    public Object likeVote(@RequestBody LikeVoteDTO likeVoteDTO,
                       HttpServletRequest request){
        System.out.println("dianzan"+likeVoteDTO.getId());
        Comment comment=commentMapper.selectByPrimaryKey(likeVoteDTO.getId());
        comment.setLikecount(comment.getLikecount()+1);
        commentMapper.updateByPrimaryKey(comment);

        System.out.println("dianzancgengong");
        return CommentResultDTO.okStatus();
    }
}
