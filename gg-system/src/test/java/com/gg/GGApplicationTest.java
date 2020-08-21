package com.gg;

import com.gg.model.system.domain.SysMenu;
import com.gg.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GGApplicationTest {

    @Autowired
    RedisUtil redisUtil;

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
        SysMenu sysMenu = (SysMenu) redisUtil.get("q::1");
        System.out.println(sysMenu.toString());
    }
}
