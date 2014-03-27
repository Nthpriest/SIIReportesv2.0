//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Empleado.java Created on 26/03/2014, 07:50:19 PM
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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class Empleado
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
@Entity
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdEmpleados", query = "SELECT e FROM Empleado e WHERE e.idEmpleados = :idEmpleados"),
    @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empleado.findByAPaterno", query = "SELECT e FROM Empleado e WHERE e.aPaterno = :aPaterno"),
    @NamedQuery(name = "Empleado.findByAMaterno", query = "SELECT e FROM Empleado e WHERE e.aMaterno = :aMaterno"),
    @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleado.findByEmail", query = "SELECT e FROM Empleado e WHERE e.email = :email")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEmpleados")
    private String idEmpleados;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "aPaterno")
    private String aPaterno;
    @Column(name = "aMaterno")
    private String aMaterno;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "empleado")
    private Collection<Solicitud> solicitudCollection;

    public Empleado() {
    }

    public Empleado(String idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    public Empleado(String idEmpleados, String nombre, String aPaterno, String aMaterno, String direccion, String telefono, String email) {
        this.idEmpleados = idEmpleados;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getIdEmpleados() {
        return idEmpleados;
    }

    public void setIdEmpleados(String idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAPaterno() {
        return aPaterno;
    }

    public void setAPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getAMaterno() {
        return aMaterno;
    }

    public void setAMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudeCollection) {
        this.solicitudCollection = solicitudeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleados != null ? idEmpleados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleados == null && other.idEmpleados != null) || (this.idEmpleados != null && !this.idEmpleados.equals(other.idEmpleados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siireportes.objetosnegocio.Empleado[ idEmpleados=" + idEmpleados + " ]";
    }
}
