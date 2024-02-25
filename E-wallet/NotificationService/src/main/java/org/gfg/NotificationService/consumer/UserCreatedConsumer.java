package org.gfg.NotificationService.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.gfg.Utils.CommonConstants;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JavaMailSender sender;

    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC, groupId = "notification-group")
    public void sendNotification(String msg) throws JsonProcessingException {
        JSONObject obj = objectMapper.readValue(msg, JSONObject.class);
        String name = (String) obj.get(CommonConstants.USER_CREATION_TOPIC_NAME);
        String email =(String) obj.get(CommonConstants.USER_CREATION_TOPIC_EMAIL);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wallet@gmail.com");
        message.setTo(email);
        message.setText("welcome " + name +" to the platform !!");
        message.setSubject("Welcom to E-wallet");

        sender.send(message);

    }
}
