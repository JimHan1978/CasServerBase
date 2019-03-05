package com.hyetec.cas.server.utils;


import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * @author: wangsaichao
 * @date: 2018/7/7
 * @description:
 */
public class PasswordSaltTest {
    private String staticSalt = ".";
    private String algorithmName = "MD5";
    private String encodedPassword = "123456";
    private String dynaSalt = "test";

    @Test
    public void test() throws Exception {
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(this.staticSalt));
        hashService.setHashAlgorithmName(this.algorithmName);
        hashService.setHashIterations(2);
        HashRequest request = new HashRequest.Builder()
                .setSalt(dynaSalt)
                .setSource(encodedPassword)
                .build();
        String res =  hashService.computeHash(request).toHex();
        System.out.println(res);
    }
}
