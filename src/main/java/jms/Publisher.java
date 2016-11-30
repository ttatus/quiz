package jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by tanya on 2016-11-30.
 */
public class Publisher {
    private TopicConnectionFactory tCF;
    private TopicConnection tCon;
    private TopicSession pubSession;
    private String topicName = "quiz-topic";
    private Topic top;
    private TopicPublisher tPub;

    public Publisher(String uname, String pwd) throws NamingException, JMSException {
        InitialContext ctx = new InitialContext();

        tCF = (TopicConnectionFactory) ctx.lookup("quiz-JMS-Topic");
        top = (Topic) ctx.lookup(topicName);

        tCon = tCF.createTopicConnection(uname, pwd);

        pubSession = tCon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        tPub = pubSession.createPublisher(top);

        tCon.start();
    }

    public void close() throws JMSException {
        tCon.stop();
    }

    public void writeMsg(String msg) throws JMSException {
        TextMessage txtMsg = pubSession.createTextMessage(msg);
        tPub.publish(txtMsg);
    }
}
