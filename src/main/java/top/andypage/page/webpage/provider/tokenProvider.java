package top.andypage.page.webpage.provider;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Component
public class tokenProvider {
    private tokenProvider(){
    }

    private static final tokenProvider instance = new tokenProvider();

    /**
     * 返回类的对象
     * @return
     */
    public static tokenProvider getInstance(){
        return instance;
    }

    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public String makeToken(){
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());

            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

