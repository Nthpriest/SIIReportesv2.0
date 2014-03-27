//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)EquipoNotFoundException.java Created on 22/03/2014, 05:47:32 PM
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

package com.siireportes.excepciones;

/**
 * Class EquipoNotFoundException
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.1
 */
public class EquipoNotFoundException extends Exception {

    /**
     * Constructor por omisión. Construye una excepción con un mensaje de error
     * nulo.
     */
    public EquipoNotFoundException() {
    }

    /**
     * Construye una excepción con el mensaje de error del parámetro.
     *
     * @param msj Mensaje de error.
     */
    public EquipoNotFoundException(String msj) {
        super(msj);
    }

    /**
     * Construye una excepción con el mensaje de error del parámetro y la causa
     * original del error.
     *
     * @param msj Mensaje de error.
     * @param causa Causa original del error.
     */
    public EquipoNotFoundException(String msj, Throwable causa) {
        super(msj, causa);
    }

    /**
     * Construye una excepción la causa original del error.
     *
     * @param causa Causa original del error.
     */
    public EquipoNotFoundException(Throwable causa) {
        super(causa);
    }
}
