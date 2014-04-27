//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)SolicitudesJpaController.java Created on 26/03/2014, 07:20:48 PM
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

import com.siireportes.objetosnegocio.Empleado;
import com.siireportes.objetosnegocio.Equipo;
import com.siireportes.objetosnegocio.Solicitud;
import com.siireportes.excepciones.NonexistentEntityException;
import com.siireportes.excepciones.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Class SolicitudesJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class SolicitudesJpaController implements Serializable {

    private EntityManagerFactory emf = null;    
    
    public SolicitudesJpaController() {
        emf = Persistence.createEntityManagerFactory("SIIRPersistenciaPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitudes) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = solicitudes.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdEmpleados());
                solicitudes.setEmpleado(empleado);
            }
            Equipo equipo = solicitudes.getEquipo();
            if (equipo != null) {
                equipo = em.getReference(equipo.getClass(), equipo.getIdEquipos());
                solicitudes.setEquipo(equipo);
            }
            em.persist(solicitudes);
            if (empleado != null) {
                empleado.getSolicitudCollection().add(solicitudes);
                empleado = em.merge(empleado);
            }
            if (equipo != null) {
                equipo.getSolicitudCollection().add(solicitudes);
                equipo = em.merge(equipo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSolicitudes(solicitudes.getFolio()) != null) {
                throw new PreexistingEntityException("Solicitudes " + solicitudes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitudes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitudes = em.find(Solicitud.class, solicitudes.getFolio());
            Empleado empleadoOld = persistentSolicitudes.getEmpleado();
            Empleado empleadoNew = solicitudes.getEmpleado();
            Equipo equipoOld = persistentSolicitudes.getEquipo();
            Equipo equipoNew = solicitudes.getEquipo();
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdEmpleados());
                solicitudes.setEmpleado(empleadoNew);
            }
            if (equipoNew != null) {
                equipoNew = em.getReference(equipoNew.getClass(), equipoNew.getIdEquipos());
                solicitudes.setEquipo(equipoNew);
            }
            solicitudes = em.merge(solicitudes);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getSolicitudCollection().remove(solicitudes);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getSolicitudCollection().add(solicitudes);
                empleadoNew = em.merge(empleadoNew);
            }
            if (equipoOld != null && !equipoOld.equals(equipoNew)) {
                equipoOld.getSolicitudCollection().remove(solicitudes);
                equipoOld = em.merge(equipoOld);
            }
            if (equipoNew != null && !equipoNew.equals(equipoOld)) {
                equipoNew.getSolicitudCollection().add(solicitudes);
                equipoNew = em.merge(equipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitudes.getFolio();
                if (findSolicitudes(id) == null) {
                    throw new NonexistentEntityException("The solicitudes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud solicitudes;
            try {
                solicitudes = em.getReference(Solicitud.class, id);
                solicitudes.getFolio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudes with id " + id + " no longer exists.", enfe);
            }
            Empleado empleado = solicitudes.getEmpleado();
            if (empleado != null) {
                empleado.getSolicitudCollection().remove(solicitudes);
                empleado = em.merge(empleado);
            }
            Equipo equipo = solicitudes.getEquipo();
            if (equipo != null) {
                equipo.getSolicitudCollection().remove(solicitudes);
                equipo = em.merge(equipo);
            }
            em.remove(solicitudes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudesEntities() {
        return findSolicitudesEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudesEntities(int maxResults, int firstResult) {
        return findSolicitudesEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int lastInsert() {
        String queryString = "select MAX(folio) from solicitudes";
        Query query = getEntityManager().createNativeQuery(queryString);
        return ((Integer) query.getSingleResult()).intValue();
    }    
    
    public Solicitud findSolicitudes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
