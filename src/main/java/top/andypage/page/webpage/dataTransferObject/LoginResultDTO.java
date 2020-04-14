package top.andypage.page.webpage.dataTransferObject;

public class LoginResultDTO {

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

    public static LoginResultDTO okStatus(){

        LoginResultDTO loginResultDTO=new LoginResultDTO();
        loginResultDTO.setCode(200);
        loginResultDTO.setMessage("Successful");
        return loginResultDTO;
    }

    public static LoginResultDTO passwordWrong(){

        LoginResultDTO loginResultDTO=new LoginResultDTO();
        loginResultDTO.setCode(201);
        loginResultDTO.setMessage("密码错误");
        return loginResultDTO;
    }

    public static LoginResultDTO usernameWrong(){

        LoginResultDTO loginResultDTO=new LoginResultDTO();
        loginResultDTO.setCode(202);
        loginResultDTO.setMessage("用户不存在");
        return loginResultDTO;
    }
    public static LoginResultDTO empty(){

        LoginResultDTO loginResultDTO=new LoginResultDTO();
        loginResultDTO.setCode(203);
        loginResultDTO.setMessage("用户名和密码不能为空");
        return loginResultDTO;
    }
}
