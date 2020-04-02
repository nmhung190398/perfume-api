package com.perfume.util;

import com.perfume.entity.Checkout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class MailUtils {

    @Value("${mail.username}")
    private String APP_EMAIL;

    @Value("${mail.password}")
    private String APP_PASSWORD;

    @Value("${mail.file-content}")
    private String fileNameContent;

    private Properties mailServerProperties;
    private Session getMailSession;
    private MimeMessage mailMessage;

    private String content;

    public MailUtils() {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

    }

    private String contentFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:" + fileNameContent);

        //File is found
        System.out.println("File Found : " + file.exists());

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println(content);


        return content;
    }

    private String setData(Checkout checkout) {
        Map<String, String> map = new HashMap<>();
        map.put("fistname", checkout.getFirstname());
        map.put("lastname", checkout.getLastname());
        map.put("address", checkout.getAddress());

        String rs = StringUtils.format(content, map);
        return rs;
    }

    public void send(Checkout checkout) {
        Thread thread = new Thread(() -> {
            try {
                this.sendHtml(checkout);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void sendHtml(Checkout checkout) throws AddressException, MessagingException {

        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(checkout.getEmail()));

        // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail

        mailMessage.setSubject("Trạng thái đơn hàng - Perfume","utf-8");
        String emailBody = "<h1>Đơn hàng đã được " + checkout.getStatus() + " </h1>";
        if (checkout.getNote() != null) {
            emailBody += "<p>Lý do: " + checkout.getNote() + "</p>";
        }
        mailMessage.setContent(emailBody, "text/html; charset=utf-8");

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", APP_EMAIL, APP_PASSWORD);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) {

    }


}
