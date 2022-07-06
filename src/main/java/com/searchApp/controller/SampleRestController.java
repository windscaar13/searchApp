package com.searchApp.controller;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SampleRestController {
	

	@GetMapping("/hello")
	public String hello(){
		return "Hello World!!!";
	}
	
	
	@GetMapping("/testMessageTopic")
	public void testMessageTopic(@RequestParam String message) throws URISyntaxException, Exception{
		Connection connection = null;
        try {
            // Producer
            //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.229.208.34:61616");
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("analyticsLogger");     
            connection.start();     
            // Publish
            String payload = message;
            Message msg = session.createTextMessage(payload);
            MessageProducer producer = session.createProducer(topic);
            System.out.println("Sending text '" + payload + "'");
            producer.send(msg);
             
            Thread.sleep(3000);
            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
	}

}
