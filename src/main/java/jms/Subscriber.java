package jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by tanya on 2016-11-30.
 */
public class Subscriber implements javax.jms.MessageListener {
    private TopicConnectionFactory tCF;
    private TopicConnection tCon;
    private TopicSession subSession;
    private String topicName = "quiz-topic";
    private Topic top;
    private TopicSubscriber tSub;

    public Subscriber(String uname, String pwd) throws NamingException,JMSException {
        InitialContext ctx = new InitialContext();

        tCF = (TopicConnectionFactory) ctx.lookup("quiz-JMS-Topic");
        top = (Topic)ctx.lookup(topicName);

        tCon = tCF.createTopicConnection(uname, pwd);

        subSession = tCon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        tSub = subSession.createSubscriber(top);
        tSub.setMessageListener(this);

        tCon.start();
    }

    public void onMessage(Message msg) {
        try {
            String text = ((TextMessage)msg).getText();

            System.out.println(text);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        try {
            tCon.close();
        } catch(JMSException je) {
            System.out.println("JMSExceptionrred");
        }
        System.exit(0);
    }
}
