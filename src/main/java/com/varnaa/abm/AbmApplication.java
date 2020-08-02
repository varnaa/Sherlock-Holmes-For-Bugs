package com.varnaa.abm;

import com.varnaa.abm.mapper.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AbmApplication implements CommandLineRunner {
    @Autowired
    private Main mapper;

    public static void main(String[] args) {
        SpringApplication.run(AbmApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        mapper.mapper();
    }
}
