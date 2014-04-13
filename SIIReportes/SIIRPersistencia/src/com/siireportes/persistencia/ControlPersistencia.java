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

import com.siireportes.excepciones.*;
import com.siireportes.objetosnegocio.*;

/**
 * Class Persistencia
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class ControlPersistencia {

    EquiposJpaController eqjc;
    EmpleadosJpaController emjc;
    SolicitudesJpaController sjc;

    protected ControlPersistencia() {
    }

    protected Equipo validaEquipo(Equipo eq) throws EquipoNotFoundException {
        eqjc = new EquiposJpaController();
        if (eqjc.findEquipos(eq.getIdEquipos()) != null) {
            return eq;
        } else {
            return null;
        }
    }

    protected Empleado validaEmpleado(Empleado em) throws EmpleadoNotFoundException {
        emjc = new EmpleadosJpaController();
        if(emjc.findEmpleados(em.getIdEmpleados()) != null) {
            return em;
        }
        else {
            return null;
        }
    }

    protected Solicitud guardarSolicitud(Solicitud s) throws PersistenciaException, PreexistingEntityException, Exception {
        sjc = new SolicitudesJpaController();
        sjc.create(s);
        return s;
    }
}
