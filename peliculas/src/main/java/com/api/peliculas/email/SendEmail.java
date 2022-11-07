package com.api.peliculas.email;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SendEmail {

  private static final String SEND_ENDPOINT = "mail/send";
  private static final String SUBJECT = "Gracias por ser parte de este proyecto!";
  private static final String TYPE = "text/plain";
  private static final String VALUE = "Disfruta de las mejores películas..";

  @Value("${email.sender.from}")
  private String emailFrom;

  @Value("${email.sender.sendgrid.token}")
  private String sendGridToken;


  public void send(String destinatario) throws IOException {
    Email from = new Email(emailFrom);
    String subject = SUBJECT;
    Email to = new Email(destinatario);
    Content content = new Content(TYPE, VALUE);
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(System.getenv(sendGridToken));
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint(SEND_ENDPOINT);
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;

    }

  }


}