package com.vrdete.email.util;

import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.util.MailSSLSocketFactory;
import com.vrdete.email.configuration.EmailConfig;
import com.vrdete.email.configuration.SenderConfig;
import com.vrdete.email.constant.ResultCode;
import com.vrdete.email.pojo.EmailStatus;
import com.vrdete.email.pojo.EmailResult;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.*;


/**
 * @author Lozt
 */
public class EmailUtil {

    public static Map<String, Object> send(SenderConfig senderConfig, EmailConfig emailConfig, TemplateEngine engine, String templateName){
        Map<String, Object> result = new HashMap<>(5);
        List<EmailStatus> failList = new ArrayList<>();
        List<EmailStatus> sucList = new ArrayList<>();
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", senderConfig.getHost());
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.smtp.port", senderConfig.getPort());
            if (senderConfig.isUseSsl()) {
                MailSSLSocketFactory sf = null;
                try {
                    sf = new MailSSLSocketFactory();
                    sf.setTrustAllHosts(true);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
                prop.put("mail.smtp.ssl.enable", "true");
                prop.put("mail.smtp.ssl.socketFactory", sf);
                prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                prop.put("mail.smtp.socketFactory.fallback", "false");
            }
            //??????JavaMail???????????????5?????????
            //1?????????session
            Session session = Session.getInstance(prop,
                    new MyAuthenticator(senderConfig.getUsername(), senderConfig.getPassword()));
            //??????Session???debug?????????????????????????????????????????????Email???????????????
            session.setDebug(senderConfig.isDebug());

            //2?????????session??????transport??????
            Transport ts = session.getTransport("smtp");
            //3?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????smtp?????????????????????????????????????????????????????????????????????????????????????????????
            ts.connect(senderConfig.getHost(), senderConfig.getUsername(), senderConfig.getPassword());

            //4???????????????
            for (String target : emailConfig.getTarget()) {
                EmailStatus vo = new EmailStatus();
                Message message = createMail(session, emailConfig, target, senderConfig.getUsername(), engine, templateName);
                vo.setTo(target);
                vo.setFrom(senderConfig.getUsername());
                vo.setEmailConfig(emailConfig);
                try{
                    ts.sendMessage(message, message.getAllRecipients());
                } catch (SMTPSendFailedException e) {
                    vo.setErrorCode(e.getReturnCode());
                    vo.setErrorMsg(e.getMessage());
                    vo.setStatus(1);

                    failList.add(vo);
                    continue;
                }
                vo.setStatus(0);
                sucList.add(vo);
            }
            ts.close();
            result.put("failList", failList);
            result.put("sucList", sucList);
            result.put("failCount", failList.size());
            result.put("sucCount", sucList.size());
            result.put("serverStatus", 0);
        } catch (MessagingException e) {
            result.put("msg", "?????????????????????,???????????????????????????SMTP?????????????????????");
            result.put("errorMsg", e.getMessage());
            result.put("serverStatus", 1);
        }

        return result;
    }



    private static Message createMail(Session session, EmailConfig emailConfig, String target, String username, TemplateEngine engine, String templateName) {
        MimeMessage message = new MimeMessage(session);
        try {
            //????????????????????????????????????
            message.setFrom(new InternetAddress(username, emailConfig.getNickname()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(target));
            MimeMultipart mp = new MimeMultipart();
            message.setSubject(emailConfig.getTitle());

            //??????????????????

            MimeBodyPart text = new MimeBodyPart();
            Context context = new Context();
            context.setVariables(emailConfig.getContextMap());
            String content = engine.process(templateName, context);
            text.setContent(content, "text/html;charset=UTF-8");
            mp.addBodyPart(text);




            //??????????????????
            if (emailConfig.getFileList() != null && emailConfig.getFileList().size() != 0) {
                for (File file : emailConfig.getFileList()) {
                    MimeBodyPart attach = new MimeBodyPart();
                    DataHandler dh = new DataHandler(new FileDataSource(file));
                    attach.setDataHandler(dh);
                    attach.setFileName(dh.getName());
                    mp.addBodyPart(attach);
                }
            }

            if(emailConfig.getImgList() != null && emailConfig.getImgList().size() != 0) {
                for (int i = 0; i < emailConfig.getImgList().size(); i++) {
                    MimeBodyPart image = new MimeBodyPart();
                    image.setDataHandler(new DataHandler(new FileDataSource(emailConfig.getImgList().get(i))));
                    image.setContentID(i + ".jpg");
                    mp.addBodyPart(image);
                }
            }
            //????????????
            mp.setSubType("mixed");

            message.setContent(mp);
            message.saveChanges();

        }catch (MessagingException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return message;
    }
}

