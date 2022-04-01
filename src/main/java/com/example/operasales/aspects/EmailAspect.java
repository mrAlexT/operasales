package com.example.operasales.aspects;

import com.example.operasales.annotations.EmailAlerting;
import com.example.operasales.domain.Customer;
import com.example.operasales.services.EmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import java.lang.reflect.Method;

@Component
@Aspect
public class EmailAspect {
    EmailService service;

    @Autowired
    public EmailAspect(EmailService service) {
        this.service = service;
    }

    @Pointcut("@annotation(com.example.operasales.annotations.EmailAlerting)")
    public void operaEventAlert() {
    }

    @After("operaEventAlert()")
    public void afterSuccessAll(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Customer customer = null;

        for (Object arg : point.getArgs()) {
            if (arg instanceof Customer) {
                customer = (Customer) arg;
                break;
            }
        }

        EmailAlerting emailAlerting = method.getAnnotation(EmailAlerting.class);
        String message = emailAlerting.value();

        if (customer != null) {
            service.send(customer.getEmail(), message);
        } else {
            service.sendAll(message);
        }
    }
}