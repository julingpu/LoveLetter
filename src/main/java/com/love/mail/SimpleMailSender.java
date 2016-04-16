package com.love.mail;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 简单邮件发送器，可单发，群发。
 *
 * @author MZULE
 *
 */
public  class SimpleMailSender {
    //如果是qq邮箱 除了要密码 要添加ssl啥的  163就不需要
    private static String myEmailAddress = "18674068464@163.com";
   private static String myEmailPassword = "jlp1204211108";

    private static  final String smtpHostName = "smtp." + myEmailAddress.split("@")[1];
    /**
     * 发送邮件的props文件
     */
    private static final transient Properties props = System.getProperties();
    /**
     * 邮件服务器登录验证
     */
    private static transient MailAuthenticator authenticator;

    /**
     * 邮箱session
     */
    private static transient Session session;


    static{
        // 初始化props
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpHostName);
        // 验证
        authenticator = new MailAuthenticator(myEmailAddress, myEmailPassword);
        // 创建session
        session = Session.getInstance(props, authenticator);
    }




    /**
     * 发送邮件
     *
     * @param recipient
     *                收件人邮箱地址
     * @param subject
     *                邮件主题
     * @param content
     *                邮件内容
     * @throws AddressException
     * @throws MessagingException
     */
    public static void send(String recipient, String subject, Object content)
            throws AddressException, MessagingException {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人
        message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        Transport.send(message);
    }


    public static void send(String recipient, SimpleMail mail)
            throws AddressException, MessagingException {
        send(recipient, mail.getSubject(), mail.getContent());
    }



}