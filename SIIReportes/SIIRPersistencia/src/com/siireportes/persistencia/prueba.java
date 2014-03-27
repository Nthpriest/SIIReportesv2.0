//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)prueba.java Created on 26/03/2014, 10:16:27 PM
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

import com.siireportes.excepciones.PreexistingEntityException;
import com.siireportes.objetosnegocio.Empleado;
import com.siireportes.objetosnegocio.Equipo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class prueba
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmpleadosJpaController ejc = new EmpleadosJpaController();
        Empleado em;
        em = new Empleado("EM0001", "Alan Alberto", "García", 
                "Peñúñuri", "Coahuila #113 nte.", "6441643157", 
                "bearz_x@hotmail.com");
        System.out.println(ejc.findEmpleados("EM0001"));
        Equipo eq;
        eq = new Equipo("EQ0001","Recepción");
        EquiposJpaController eqjc = new EquiposJpaController();
        try {
            eqjc.create(eq);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
