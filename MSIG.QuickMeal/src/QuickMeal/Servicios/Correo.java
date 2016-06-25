/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Servicios;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo {

    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param to
     * @param subject
     * @param body
     */
    public static void enviar(Session session, String from, String to, String subject, String body, String file) {
        try {
            MimeMessage m = new MimeMessage(session);
            m.addHeader("Content-type", "text/HTML; charset=UTF-8");
            m.addHeader("format", "flowed");
            m.addHeader("Content-Transfer-Encoding", "8bit");

            //crear texto del mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setText(body);

            //crear archivo adjunto
            BodyPart adjunto = new MimeBodyPart();
            try {
                adjunto.setDataHandler(new DataHandler(new FileDataSource(file)));
                adjunto.setFileName(file);
            } catch(Exception e) {
                file = null;
            }

            //adjuntar contenido al cuerpo del mensaje
            MimeMultipart contenido = new MimeMultipart();
            contenido.addBodyPart(texto);
            if(file == null) { m.setText(body); } else { contenido.addBodyPart(adjunto); }

            m.setFrom(new InternetAddress(from, subject));
            m.setReplyTo(InternetAddress.parse(to, false));
            m.setSubject(subject, "UTF-8");
            
            //verificar si hay algun archivo adjunto
            m.setContent(contenido);
            
            m.setSentDate(new Date());
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            Transport.send(m);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
}
