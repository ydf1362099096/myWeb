package top.andypage.page.webpage.dataTransferObject;


import top.andypage.page.webpage.Exception.CustomErrorCode;
import top.andypage.page.webpage.Exception.CustomException;

public class CommentResultDTO {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static CommentResultDTO errorOf(Integer code, String message){
        CommentResultDTO commentResultDTO=new CommentResultDTO();
        commentResultDTO.setCode(code);
        commentResultDTO.setMessage(message);
        return commentResultDTO;
    }

    public static CommentResultDTO errorOf(CustomException e){

        return errorOf(e.getCode(),e.getMessage());
    }

    public static CommentResultDTO errorOf(CustomErrorCode e){
        return errorOf(e.getCode(),e.getMessage());
    }

    public static CommentResultDTO okStatus(){

        CommentResultDTO commentResultDTO=new CommentResultDTO();
        commentResultDTO.setCode(200);
        commentResultDTO.setMessage("Successful");
        return commentResultDTO;
    }

}
