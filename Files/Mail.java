package com.system.fms;

import java.util.*;
import javax.mail.internet.*;
import javax.mail.*;
import javax.activation.*;
/*
 * @author Soumyajit
 */
class GmailAuthenticator extends Authenticator {
    String user, password;
    public GmailAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }
}
class Mail {
    public int send(InternetAddress[] recipients, int type) {
        
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            
            Session session = Session.getInstance(props, new GmailAuthenticator("dedsec4706@gmail.com", "Trojanx98016"));
            
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, recipients);
            
            if(type == 0) {
                MimeMultipart content = new MimeMultipart();
                
                MimeBodyPart bodypart1 = new MimeBodyPart();
                bodypart1.setText("Your Receipt is generated. Download it from here");
                MimeBodyPart bodypart2 = new MimeBodyPart();
                bodypart2.setDataHandler(new DataHandler(new FileDataSource("D:/new.pdf")));
                bodypart2.setFileName("Receipt.pdf");
                
                content.addBodyPart(bodypart1);
                content.addBodyPart(bodypart2);
                
                message.setSubject("Fee Receipt");
                message.setContent(content);
            }
            else{
                message.setSubject("Due Fees");
                message.setText("Kindly pay your fees as soon as Possible\nIf there is any issue, Kindly contact FeeDepartment");
            }
           
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html"); 
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml"); 
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain"); 
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed"); 
            mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822"); 
            
            Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
            Transport.send(message);
        }
        catch(Exception e) { e.printStackTrace(); }
        return 1;
    }
    public void receive() {
        
    }
}
