package com.love.controller;

import coffee.annotation.RequestMapping;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.love.mail.SimpleMailSender;

/**
 * created by julingpu on 2016/4/16
 **/
public class UserController {

    private static transient Session session;



    @RequestMapping(type = "post",url = "/sendLoginCodeByEmail")
    public void sendLoginCodeByEmail(String email){
        System.out.print("1");

        List<String> recipients = new ArrayList<String>();
        recipients.add(email);
        try {
            for (String recipient : recipients) {
                SimpleMailSender.send(recipient, "123","456");
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }





}
