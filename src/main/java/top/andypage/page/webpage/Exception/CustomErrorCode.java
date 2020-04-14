package top.andypage.page.webpage.Exception;

public enum CustomErrorCode implements ICustomErrorCode {
    USER_NOT_LOGIN(2001, "用户未登陆,请先登陆!"),
    TOPIC_NOT_FOUND(2002,"话题不存在或已被删除!"),
    SYSTEM_ERROR(2003,"系统出错，请稍后再试!"),
    PAGE_NOT_EXIST(2004,"您访问的页面不存在！"),
    REPLY_TARGET_NOT_EXIST(2005,"您回复的问题不存在！"),
    REPLY_CONTENT_NULL(2006,"回复不能为空！");


    private String message;
    private Integer code;

    CustomErrorCode(Integer code, String message) {
        this.code=code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
