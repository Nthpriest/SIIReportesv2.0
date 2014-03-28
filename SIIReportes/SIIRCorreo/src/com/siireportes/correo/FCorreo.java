//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Correo.java Created on 22/03/2014, 04:22:34 PM
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
import com.siireportes.interfacescorreo.ICorreo;
import com.siireportes.objetosnegocio.Solicitud;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;


/**
 * Class Correo
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class FCorreo implements ICorreo {

    ControlCorreo co;

    public FCorreo() {
    }

    /**
     *
     * @param s
     * @return
     * @throws CorreoException
     * @throws MessagingException
     */
    @Override
    public boolean enviarCorreo(Solicitud s) throws CorreoException {
        co = new ControlCorreo();        
        try {
            return co.enviarCorreo(s);
        } catch (MessagingException ex) {
            Logger.getLogger(FCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
        
    }

}