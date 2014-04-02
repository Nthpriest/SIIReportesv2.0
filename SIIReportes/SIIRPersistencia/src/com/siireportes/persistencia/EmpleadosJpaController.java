//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)EmpleadosJpaController.java Created on 26/03/2014, 07:20:47 PM
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
import com.siireportes.objetosnegocio.Solicitud;
import com.siireportes.excepciones.NonexistentEntityException;
import com.siireportes.excepciones.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class EmpleadosJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class EmpleadosJpaController implements Serializable {

    private EntityManagerFactory emf = null;
    
    public EmpleadosJpaController() {
        emf = Persistence.createEntityManagerFactory("SIIRPersistenciaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleados) throws PreexistingEntityException, Exception {
        if (empleados.getSolicitudCollection() == null) {
            empleados.setSolicitudCollection(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Solicitud> attachedSolicitudesCollection = new ArrayList<Solicitud>();
            for (Solicitud solicitudesCollectionSolicitudesToAttach : empleados.getSolicitudCollection()) {
                solicitudesCollectionSolicitudesToAttach = em.getReference(solicitudesCollectionSolicitudesToAttach.getClass(), solicitudesCollectionSolicitudesToAttach.getFolio());
                attachedSolicitudesCollection.add(solicitudesCollectionSolicitudesToAttach);
            }
            empleados.setSolicitudCollection(attachedSolicitudesCollection);
            em.persist(empleados);
            for (Solicitud solicitudesCollectionSolicitudes : empleados.getSolicitudCollection()) {
                Empleado oldEmpleadoOfSolicitudesCollectionSolicitudes = solicitudesCollectionSolicitudes.getEmpleado();
                solicitudesCollectionSolicitudes.setEmpleado(empleados);
                solicitudesCollectionSolicitudes = em.merge(solicitudesCollectionSolicitudes);
                if (oldEmpleadoOfSolicitudesCollectionSolicitudes != null) {
                    oldEmpleadoOfSolicitudesCollectionSolicitudes.getSolicitudCollection().remove(solicitudesCollectionSolicitudes);
                    oldEmpleadoOfSolicitudesCollectionSolicitudes = em.merge(oldEmpleadoOfSolicitudesCollectionSolicitudes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleados(empleados.getIdEmpleados()) != null) {
                throw new PreexistingEntityException("Empleados " + empleados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleados = em.find(Empleado.class, empleados.getIdEmpleados());
            Collection<Solicitud> solicitudesCollectionOld = persistentEmpleados.getSolicitudCollection();
            Collection<Solicitud> solicitudesCollectionNew = empleados.getSolicitudCollection();
            Collection<Solicitud> attachedSolicitudesCollectionNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudesCollectionNewSolicitudesToAttach : solicitudesCollectionNew) {
                solicitudesCollectionNewSolicitudesToAttach = em.getReference(solicitudesCollectionNewSolicitudesToAttach.getClass(), solicitudesCollectionNewSolicitudesToAttach.getFolio());
                attachedSolicitudesCollectionNew.add(solicitudesCollectionNewSolicitudesToAttach);
            }
            solicitudesCollectionNew = attachedSolicitudesCollectionNew;
            empleados.setSolicitudCollection(solicitudesCollectionNew);
            empleados = em.merge(empleados);
            for (Solicitud solicitudesCollectionOldSolicitudes : solicitudesCollectionOld) {
                if (!solicitudesCollectionNew.contains(solicitudesCollectionOldSolicitudes)) {
                    solicitudesCollectionOldSolicitudes.setEmpleado(null);
                    solicitudesCollectionOldSolicitudes = em.merge(solicitudesCollectionOldSolicitudes);
                }
            }
            for (Solicitud solicitudesCollectionNewSolicitudes : solicitudesCollectionNew) {
                if (!solicitudesCollectionOld.contains(solicitudesCollectionNewSolicitudes)) {
                    Empleado oldEmpleadoOfSolicitudesCollectionNewSolicitudes = solicitudesCollectionNewSolicitudes.getEmpleado();
                    solicitudesCollectionNewSolicitudes.setEmpleado(empleados);
                    solicitudesCollectionNewSolicitudes = em.merge(solicitudesCollectionNewSolicitudes);
                    if (oldEmpleadoOfSolicitudesCollectionNewSolicitudes != null && !oldEmpleadoOfSolicitudesCollectionNewSolicitudes.equals(empleados)) {
                        oldEmpleadoOfSolicitudesCollectionNewSolicitudes.getSolicitudCollection().remove(solicitudesCollectionNewSolicitudes);
                        oldEmpleadoOfSolicitudesCollectionNewSolicitudes = em.merge(oldEmpleadoOfSolicitudesCollectionNewSolicitudes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = empleados.getIdEmpleados();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleados;
            try {
                empleados = em.getReference(Empleado.class, id);
                empleados.getIdEmpleados();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            Collection<Solicitud> solicitudesCollection = empleados.getSolicitudCollection();
            for (Solicitud solicitudesCollectionSolicitudes : solicitudesCollection) {
                solicitudesCollectionSolicitudes.setEmpleado(null);
                solicitudesCollectionSolicitudes = em.merge(solicitudesCollectionSolicitudes);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleados(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Empleado> getEmpleadosPorNombreLike(String nombre) {
        String queryString = "SELECT e FROM Empleado e WHERE LOWER(e.nombre) LIKE :nombre";
        Query query = getEntityManager().createQuery(queryString);

        query.setParameter("nombre", nombre.toLowerCase() + '%');
        return query.getResultList();
    }
    
    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
