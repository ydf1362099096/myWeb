package top.andypage.page.webpage.Exception;

public class CustomException extends RuntimeException{

    private String message;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public CustomException(CustomException errorCode) {
        this.code=errorCode.code;
        this.message = errorCode.message;
    }

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(Integer code,String message) {
        this.code=code;
        this.message = message;
    }

    public CustomException(CustomErrorCode eCode) {
        this.code=eCode.getCode();
        this.message=eCode.getMessage();
    }

    public String getMessge() {
        return message;
    }

}
