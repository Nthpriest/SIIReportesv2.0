//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FPersistencia.java Created on 22/03/2014, 05:08:48 PM
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
import com.siireportes.interfacespersistencia.IPersistencia;
import com.siireportes.objetosnegocio.Solicitud;

/**
 * Class FPersistencia
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class FPersistencia implements IPersistencia {

    ControlPersistencia cp;

    public FPersistencia() {
    }

    @Override
    public Solicitud guardarSolicitud(Solicitud s) throws PersistenciaException, PreexistingEntityException, Exception {
        cp = new ControlPersistencia();        
        return cp.guardarSolicitud(s);
    }

}
