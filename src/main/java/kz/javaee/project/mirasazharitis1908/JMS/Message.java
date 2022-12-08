package kz.javaee.project.mirasazharitis1908.JMS;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class Message {

  @Resource
  private ConnectionFactory connectionFactory;

  @Resource
  private Queue queue;

  public void sendMessage(String text) throws JMSException {
    Connection connection = null;
    Session session = null;
    try {
      connection = connectionFactory.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      MessageProducer producer = session.createProducer(queue);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

      TextMessage textMessage = session.createTextMessage(text);
      producer.send(textMessage);

    } finally {
      if (session != null) {
        session.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
  }

  public String receiveMessage() throws JMSException {
    Connection connection = null;
    Session session = null;
    MessageConsumer consumer = null;
    try {
      connection = connectionFactory.createConnection();
      connection.start();

      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      consumer = session.createConsumer(queue);

      TextMessage message = (TextMessage) consumer.receive(1000);

      return message.getText();
    } finally {
      if (consumer != null) {
        consumer.close();
      }
      if (session != null) {
        session.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Transactional
  public List<String> receiveAll() throws JMSException {
    Connection connection = null;
    Session session = null;
    MessageConsumer consumer = null;
    List<String> messages = new ArrayList<>();
    try {
      connection = connectionFactory.createConnection();
      connection.start();

      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      consumer = session.createConsumer(queue);

      TextMessage message;
      while ((message = (TextMessage) consumer.receive(1000)) != null) {
        messages.add(message.getText());
      }
      return messages;

    } finally {
      if (consumer != null) {
        consumer.close();
      }
      if (session != null) {
        session.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
  }

}