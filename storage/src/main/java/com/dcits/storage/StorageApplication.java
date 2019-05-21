package com.dcits.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@SpringBootApplication
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}

/**
 * 外部Tomcat部署使用如下代码，使用Tomcat8及以上版本
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
//public class SupervisionApplication extends SpringBootServletInitializer {
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SupervisionApplication.class);
//    }
//
//	public static void main(String[] args) {
//		SpringApplication.run(SupervisionApplication.class, args);
//	}
//}
