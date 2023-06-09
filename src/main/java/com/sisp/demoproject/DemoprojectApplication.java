package com.sisp.demoproject;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sisp.demoproject.dao")
public class DemoprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoprojectApplication.class, args);
    }

}
