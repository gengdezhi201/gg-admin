package com.gg;

import com.alibaba.fastjson.parser.ParserConfig;
import com.gg.model.security.util.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开启审计功能 -> @EnableJpaAuditing
 * @author gengdz
 * @date 2020年6月16日21:29:45
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.gg.model.*.mapper")
public class GGApplication {

    public static void main(String[] args) {
        SpringApplication.run(GGApplication.class, args);
    }


}
