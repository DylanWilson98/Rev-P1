package com.dylan;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

    Logger logger = Logger.getLogger(UserAspect.class);

    @After("execution(* com.dylan.controller.UserController.*(..))")
    public  void beforeGettingUser(JoinPoint joinPoint) {

        logger.info(joinPoint.getSignature().getName() + " called...");
    }
}