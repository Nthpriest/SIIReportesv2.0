//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FAdmonEquipos.java Created on 22/03/2014, 04:41:20 PM
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
package com.siireportes.admonequipos;

import com.siireportes.excepciones.EquipoNotFoundException;
import com.siireportes.interfacesnegocio.IAdmonEquipos;
import com.siireportes.objetosnegocio.Equipo;

/**
 * Class FAdmonEquipos
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class FAdmonEquipos implements IAdmonEquipos {

    ControlAdmonEquipos caeq;

    public FAdmonEquipos() {
    }

    @Override
    public Equipo validaEquipo(Equipo e) throws EquipoNotFoundException {
        caeq = new ControlAdmonEquipos();        
        return caeq.validaEquipo(e);
    }
}