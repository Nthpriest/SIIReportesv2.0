//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)ControlCorreo.java Created on 22/03/2014, 04:49:41 PM
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses>
 *
 * Copyright (C) 2014 Alan García. All rights reserved.
 */
//</editor-fold>
package com.siireportes.correo;

import com.siireportes.excepciones.CorreoException;
import com.siireportes.objetosnegocio.Solicitud;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Class ControlCorreo
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class ControlCorreo {

    protected ControlCorreo() {
    }

    protected boolean enviarCorreo(Solicitud s) throws CorreoException, MessagingException {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "siireportes@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("siireportes@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("aagpzx@gmail.com"));
            message.setSubject("Solicitud #" + s.getFolio());
            message.setText("Se genero una nueva Solicitud \n"
                    + "Folio: " + s.getFolio() + "\n"
                    + "Equipo: " + s.getEquipo().getIdEquipos() + " " 
                    + "("+ s.getEquipo().getArea() + ")" + "\n"
                    + "Por: " + s.getEmpleado().getIdEmpleados() + " "
                    + "("+ s.getEmpleado().getNombre() + " " 
                    + s.getEmpleado().getAPaterno() + " " 
                    + s.getEmpleado().getAMaterno() + ")"
                    + "\n" + "Problema: " + s.getProblema() + "\n" + "Observaciones: "
                    + s.getObservaciones() + "\n" + "Fecha: " + s.getFecha());

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("siireportes@gmail.com", "9517538426");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        return true;
    }
}