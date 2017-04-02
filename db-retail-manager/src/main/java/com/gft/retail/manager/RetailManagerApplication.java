package com.gft.retail.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RetailManagerApplication {

	static private ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(RetailManagerApplication.class, args);
    }

    public static void stop(String[] args) {
        System.exit(SpringApplication.exit(ctx));
    }
    
}
