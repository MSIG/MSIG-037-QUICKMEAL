/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Servicios;

/**
 *
 * @author Derwin Santa Cruz
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

	public void send() {

		final String username = "pantaleon.club@gmail.com";
		final String password = "Derwin2012mg";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pantaleon.club@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("windermsn@hotmail.com"));
			message.setSubject("Prueba");
			message.setText("Esto es una prueba de correo");

			Transport.send(message);

			System.out.println("Enviado correctamente");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}