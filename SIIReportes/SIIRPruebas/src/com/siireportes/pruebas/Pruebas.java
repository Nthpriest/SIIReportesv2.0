//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Pruebas.java Created on 22/03/2014, 05:30:48 PM
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
package com.siireportes.pruebas;

import com.siireportes.admonsolicitudes.FAdmonSolicitudes;
import com.siireportes.excepciones.*;
import com.siireportes.interfacesnegocio.IAdmonSolicitudes;
import com.siireportes.objetosnegocio.*;
import com.siireportes.persistencia.EmpleadosJpaController;
import com.siireportes.persistencia.EquiposJpaController;
import com.siireportes.persistencia.SolicitudesJpaController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class Pruebas
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PreexistingEntityException, Exception {
        // TODO code application logic here
        EmpleadosJpaController emjc = new EmpleadosJpaController();
        EquiposJpaController eqjc = new EquiposJpaController();  
        SolicitudesJpaController sjc = new SolicitudesJpaController();
        //int counter = emjc.getEmpleadosCount()+1;
        //String counterToString = "EM"+Integer.toString(counter);
        Empleado em = new Empleado("EM0001", "Alan Alberto", "García",
                "Peñúñuri", "Coahuila #113 nte.", "6441643157",
                "bearz_x@hotmail.com");
        Equipo eq = new Equipo("EQ0001", "Recepción");
        //emjc.create(em);
        //eqjc.create(eq);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date);
        //int scounter = sjc.getSolicitudesCount()+1;
        Solicitud s = new Solicitud(7, eq, em, "Monitor",
                "No enciende el monitor", fecha);
        IAdmonSolicitudes fads = new FAdmonSolicitudes();
        try {
            if (fads.registrarSolicitud(s) != null) {
                System.out.println("Solicitud realizada con folio: " + s.getFolio());
            } else {
                System.out.println("Error");
            }
        } catch (EquipoNotFoundException | EmpleadoNotFoundException |
                PersistenciaException | CorreoException ex) {
            System.out.println("No se pudo realizar el registro");
        }
    }
}
