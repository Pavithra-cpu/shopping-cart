package queryclass;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import queryclass.Order;

public class SendEmail{
  public void sendMail(String email,List<Order> order){
    String from = "deepakpandi58@gmail.com";
    final String username = "deepakpandi58@gmail.com";
    final String password = "######;;;roj011198";
    String host = "smtp.gmail.com";
    //Order O = new Order();
    Properties props = new Properties();
    props.put("mail.smtp.auth","true");
    props.put("mail.smtp.starttls.enable","true");
    props.put("mail.smtp.host",host);
    props.put("mail.smtp.port","587");

    Session session = Session.getInstance(props,
      new Authenticator(){
        protected PasswordAuthentication getPasswordAuthentication(){
          return new PasswordAuthentication(username,password);
        }
      });
    try{
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
      message.setSubject("Order Details:Shopping cart");
      BodyPart msgbody = new MimeBodyPart();
      msgbody.setText("Hello!The items you have ordered is listed below:\n");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(msgbody);
      msgbody = new MimeBodyPart();
      msgbody.setText("Itemname\tQuantity\tPrice\n");
      multipart.addBodyPart(msgbody);
      for(Order O : order){
        msgbody = new MimeBodyPart();
        msgbody.setText(O.getIname()+"\t\t\t"+O.getPquantity()+"\t\t"+O.getPprice()+"\n");
        multipart.addBodyPart(msgbody);
      }
      message.setContent(multipart);
      Transport.send(message);
    }
    catch(MessagingException e){
      throw new RuntimeException(e);
    }
  }
}
