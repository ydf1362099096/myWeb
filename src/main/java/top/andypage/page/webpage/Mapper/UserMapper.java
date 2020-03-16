package top.andypage.page.webpage.Mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import top.andypage.page.webpage.model.User;

@Mapper
@Component
public interface UserMapper {

    @Insert("INSERT INTO NEW_SCHEMA.USER (username, password, create_time, modified_time,token) values(#{username},#{password},#{create_time},#{modified_time},#{token})")
    void insert(User user);

    @Select("SELECT * From NEW_SCHEMA.USER WHERE token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT * From NEW_SCHEMA.USER WHERE username = #{username}")
    User findByUser(@Param("username") String username);

    @Update("UPDATE NEW_SCHEMA.USER SET token = null where username = #{username}")
    void deleteToken(String username);

    @Update("UPDATE NEW_SCHEMA.USER SET token = #{token} where username = #{username}")
    void updateToken(@Param("token") String token,
                     @Param("username") String username);
}
