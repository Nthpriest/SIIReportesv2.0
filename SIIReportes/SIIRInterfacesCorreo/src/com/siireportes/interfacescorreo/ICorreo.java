//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)ICorreo.java Created on 22/03/2014, 04:09:15 PM
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

package com.siireportes.interfacescorreo;

import com.siireportes.excepciones.CorreoException;
import com.siireportes.objetosnegocio.Solicitud;

/**
 * Class ICorreo
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public interface ICorreo {

    boolean enviarCorreo(Solicitud e) throws CorreoException;
    
}
