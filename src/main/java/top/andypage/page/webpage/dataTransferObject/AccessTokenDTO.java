package top.andypage.page.webpage.dataTransferObject;

public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public void set_Client_id(String client_id) {
        this.client_id = client_id;
    }

    public void set_Client_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public void set_Code(String code) {
        this.code = code;
    }

    public void set_Redirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public void set_State(String state) {
        this.state = state;
    }

    public String get_Client_id() {
        return client_id;
    }

    public String get_client_secret() {
        return client_secret;
    }

    public String get_Code() {
        return code;
    }

    public String get_Redirect_uri() {
        return redirect_uri;
    }

    public String get_State() {
        return state;
    }
}
