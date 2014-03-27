//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Persistencia.java Created on 22/03/2014, 04:17:12 PM
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

package com.siireportes.persistencia;

import com.siireportes.excepciones.PersistenciaException;
import com.siireportes.excepciones.PreexistingEntityException;
import com.siireportes.objetosnegocio.Solicitud;

/**
 * Class Persistencia
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class ControlPersistencia {

    protected ControlPersistencia() {
    }

    protected Solicitud guardarSolicitud(Solicitud s) throws PersistenciaException, PreexistingEntityException, Exception {
        SolicitudesJpaController sjc = new SolicitudesJpaController();
        sjc.create(s);
        return s;
    }

}
