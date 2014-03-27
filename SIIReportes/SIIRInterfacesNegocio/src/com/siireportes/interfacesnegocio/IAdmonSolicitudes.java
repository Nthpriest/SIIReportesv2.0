//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)IAdmonSolicitudes.java Created on 22/03/2014, 04:09:08 PM
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

package com.siireportes.interfacesnegocio;

import com.siireportes.excepciones.CorreoException;
import com.siireportes.excepciones.EmpleadoNotFoundException;
import com.siireportes.excepciones.EquipoNotFoundException;
import com.siireportes.excepciones.PersistenciaException;
import com.siireportes.excepciones.PreexistingEntityException;
import com.siireportes.objetosnegocio.Solicitud;

/**
 * Class IAdmonSolicitudes
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public interface IAdmonSolicitudes {

    Solicitud registrarSolicitud(Solicitud s) throws 
            EquipoNotFoundException, EmpleadoNotFoundException, 
            PersistenciaException, CorreoException, PreexistingEntityException, Exception;  
    
}
