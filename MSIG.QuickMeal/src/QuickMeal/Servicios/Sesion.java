/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Servicios;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sesion {

    public Sesion(final String from, final String password, String to, String subject, String body, String file) throws UnsupportedEncodingException {

        System.out.println("Iniciando parametros de gmail");
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        System.out.println("Iniciando sesion " + from);
        Session session = Session.getInstance(p,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        System.out.println("Sesion iniciada");

        try {

            System.out.println("Construyendo mensaje");
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
            
            System.out.println("Enviando correo a " + to);
            
            m.setSentDate(new Date());
            System.out.println("Fecha asignada a correo");
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            System.out.println("Enviando.....");
            Transport.send(m);

            System.out.println("Enviado correctamente");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}

