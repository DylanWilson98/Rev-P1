package com.dylan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);

       // ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");




















//
//        UserService us = new UserService();
//        UserRepo ur = new UserRepo();
//        us.createAccount("dylan", "wilson");
//        us.createAccount("test", "test");
        //System.out.println(ur.getByUsername("test"));
    }
}
