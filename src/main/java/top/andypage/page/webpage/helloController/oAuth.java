package top.andypage.page.webpage.helloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.andypage.page.webpage.dataTransferObject.AccessTokenDTO;
import top.andypage.page.webpage.provider.GitAuthProvider;

@Controller
public class oAuth {
    @Autowired
    private GitAuthProvider myGitAuthProvider;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.set_Client_id("922a5d8ff2f97a592a32");
        accessTokenDTO.set_Client_secret("68f6f674fab0854071c43cc8fac8dfc8b45e7833");
        accessTokenDTO.set_Code(code);
        accessTokenDTO.set_Redirect_uri("http://localhost:9876/callback");
        accessTokenDTO.set_State(state);
        System.out.println(accessTokenDTO.get_Client_id());
        System.out.println(accessTokenDTO.get_Code());
        System.out.println(accessTokenDTO.get_client_secret());
        System.out.println(accessTokenDTO.get_Redirect_uri());
        System.out.println(accessTokenDTO.get_State());

        myGitAuthProvider.getToken(accessTokenDTO);

        return "index";
    }
}
