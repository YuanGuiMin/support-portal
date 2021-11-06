package com.meowu.support.portal.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class SupportServiceApplication{

    public static void main(String[] args){
        SpringApplication.run(SupportServiceApplication.class, args);
    }
}
