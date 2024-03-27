package com.shdatalink.todo;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@MapperScan("com.shdatalink.common.mapper")
@EnableEventHandlers
@Import({EventuateDriverConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.shdatalink.**")
public class TodoServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(TodoServiceMain.class, args);
    }
}
