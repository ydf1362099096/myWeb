package top.andypage.page.webpage.Mapper;




import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import top.andypage.page.webpage.model.Topic;

import java.util.List;


@Mapper
@Component
public interface TopicMapper {

    @Insert("INSERT INTO NEW_SCHEMA.TOPIC (TITLE, DESCRIPTION, CREATE_TIME, MODIFIED_TIME,PUBLISHER_ID,COMMENT_COUNT," +
            "VIEW_COUNT,LIKE_COUNT) values(#{title},#{description},#{create_time},#{modified_time},#{publisher_id},#{comment_count}" +
            ",#{view_count},#{like_count})")
    void insertTopic(Topic topic);

    @Select("SELECT * FROM NEW_SCHEMA.TOPIC")
    List<Topic> list();

    @Select("SELECT * FROM NEW_SCHEMA.TOPIC LIMIT #{pageNum}, #{size}")
    List<Topic> listPage(Integer pageNum,Integer size);

    @Select("SELECT COUNT(1) FROM NEW_SCHEMA.TOPIC")
    Integer count();

    @Select("SELECT * FROM NEW_SCHEMA.TOPIC WHERE ID = #{id}")
    Topic getTopicDto(Integer id);

    @Select("SELECT * FROM NEW_SCHEMA.TOPIC WHERE PUBLISHER_ID = #{p_id} LIMIT #{startIndex}, #{size}")
    List<Topic> getTopicByPublisher(Integer p_id, Integer startIndex, Integer size);
}

