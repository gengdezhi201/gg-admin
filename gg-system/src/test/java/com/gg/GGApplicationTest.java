package com.gg;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.gg.model.system.domain.SysMenu;
import com.gg.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.Locale;

//@SpringBootTest
public class GGApplicationTest {

    @Autowired
    RedisUtils redisUtil;

    @Test()
    void test01() {
        SysMenu sysMenu = new SysMenu(1);
        sysMenu.setCache(true);
        sysMenu.setHidden(false);
        sysMenu.setCreateUserName("1231");
        sysMenu.setMenuSort(1);
        redisUtil.set("q::1",sysMenu,1000L);
    }

    @Test
    void test02() {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 9);
        //ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.setBackground(Color.WHITE);
        captcha.write("d:/shear.png");
        //验证图形验证码的有效性，返回boolean值
        captcha.verify("1234");
    }

    @Test
    void test03(){
        System.out.printf(Locale.JAPANESE,"字符数");
    }
}
