package top.andypage.page.webpage.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import top.andypage.page.webpage.dataTransferObject.AccessTokenDTO;
import top.andypage.page.webpage.dataTransferObject.GithubUser;

import java.io.IOException;

@Component
public class GitAuthProvider {
    public String getToken(AccessTokenDTO accessTokenDTO){
        MediaType myJson = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        System.out.println(JSON.toJSONString(accessTokenDTO));
            RequestBody body = RequestBody.create(myJson, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String myString= response.body().string();
                System.out.println(myString);
                return myString;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();

            try{
                Response response = client.newCall(request).execute();
                String myString= response.body().string();
                GithubUser githubUser=JSON.parseObject(myString,GithubUser.class);
                return githubUser;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
}
