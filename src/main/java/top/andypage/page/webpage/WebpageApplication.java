package top.andypage.page.webpage;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.andypage.page.webpage.Mapper")
public class WebpageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebpageApplication.class, args);
    }

}
