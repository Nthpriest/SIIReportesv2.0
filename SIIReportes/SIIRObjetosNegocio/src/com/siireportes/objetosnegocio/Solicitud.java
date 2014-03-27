//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Solicitude.java Created on 26/03/2014, 07:50:19 PM
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
package com.siireportes.objetosnegocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Solicitude
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
@Entity
@Table(name = "solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByFolio", query = "SELECT s FROM Solicitud s WHERE s.folio = :folio"),
    @NamedQuery(name = "Solicitud.findByProblema", query = "SELECT s FROM Solicitud s WHERE s.problema = :problema"),
    @NamedQuery(name = "Solicitud.findByObservaciones", query = "SELECT s FROM Solicitud s WHERE s.observaciones = :observaciones"),
    @NamedQuery(name = "Solicitud.findByFecha", query = "SELECT s FROM Solicitud s WHERE s.fecha = :fecha")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "folio")
    private Integer folio;
    @Column(name = "problema")
    private String problema;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fecha")
    private String fecha;
    @JoinColumn(name = "Empleado", referencedColumnName = "idEmpleados")
    @ManyToOne
    private Empleado empleado;
    @JoinColumn(name = "Equipo", referencedColumnName = "idEquipos")
    @ManyToOne
    private Equipo equipo;

    public Solicitud() {
    }

    public Solicitud(Integer folio) {
        this.folio = folio;
    }

    public Solicitud(Integer folio, Equipo equipo, Empleado empleado, String problema, String observaciones, String fecha) {
        this.folio = folio;
        this.equipo = equipo;
        this.empleado = empleado;
        this.problema = problema;
        this.observaciones = observaciones;
        this.fecha = fecha;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (folio != null ? folio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.folio == null && other.folio != null) || (this.folio != null && !this.folio.equals(other.folio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siireportes.objetosnegocio.Solicitud[ folio=" + folio + " ]";
    }
}
