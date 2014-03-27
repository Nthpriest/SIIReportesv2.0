//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)ControlAdmonSolicitudes.java Created on 22/03/2014, 05:12:03 PM
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
package com.siireportes.admonsolicitudes;

import com.siireportes.admonempleados.FAdmonEmpleados;
import com.siireportes.admonequipos.FAdmonEquipos;
import com.siireportes.correo.FCorreo;
import com.siireportes.excepciones.*;
import com.siireportes.interfacesnegocio.IAdmonEmpleados;
import com.siireportes.interfacesnegocio.IAdmonEquipos;
import com.siireportes.interfacescorreo.ICorreo;
import com.siireportes.interfacespersistencia.IPersistencia;
import com.siireportes.objetosnegocio.*;
import com.siireportes.persistencia.FPersistencia;

/**
 * Class ControlAdmonSolicitudes
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class ControlAdmonSolicitudes {

    IAdmonEmpleados em;
    IAdmonEquipos eq;
    ICorreo co;
    IPersistencia pe;

    protected ControlAdmonSolicitudes() {
    }

    protected Solicitud registrarSolicitud(Solicitud s) throws
            EquipoNotFoundException, EmpleadoNotFoundException,
            PersistenciaException, CorreoException, PreexistingEntityException, Exception {
        em = new FAdmonEmpleados();
        eq = new FAdmonEquipos();
        pe = new FPersistencia();
        co = new FCorreo();
        if (eq.validaEquipo(s.getEquipo()) != null ||
                em.validaEmpleado(s.getEmpleado()) != null) {
            pe.guardarSolicitud(s);
            co.enviarCorreo(s);
            return s;
        } else {
            return null;
        }
    }
}
