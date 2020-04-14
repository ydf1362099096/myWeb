package top.andypage.page.webpage.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.andypage.page.webpage.Exception.CustomErrorCode;
import top.andypage.page.webpage.Exception.CustomException;
import top.andypage.page.webpage.dataTransferObject.CommentResultDTO;
import top.andypage.page.webpage.model.Comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ErrorException {

    @ExceptionHandler(Exception.class)
    Object handleControllerException(HttpServletRequest request,
                                           Model model,Throwable ex,
                                           HttpServletResponse response) {
        HttpStatus status = getStatus(request);

        if("application/json".equals(request.getContentType())){
            System.out.println("json出错");
            CommentResultDTO commentResultDTO;
            if(ex instanceof CustomException){
                Integer code=((CustomException) ex).getCode();
                String string=((CustomException) ex).getMessge();
                commentResultDTO = CommentResultDTO.errorOf(code,string);
            }else{
                commentResultDTO = CommentResultDTO.errorOf(CustomErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setCharacterEncoding("UTF-8");
                response.setStatus(200);
                response.setContentType("application/json");
                PrintWriter writer=response.getWriter();
                writer.write(JSON.toJSONString(commentResultDTO));
                System.out.println(JSON.toJSONString(commentResultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            System.out.println("非json出错");
            if(ex instanceof CustomException){
                model.addAttribute("message",((CustomException) ex).getMessge());
            }else{
                model.addAttribute("message",CustomErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }


    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
