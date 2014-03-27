//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)EquiposJpaController.java Created on 26/03/2014, 07:20:48 PM
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

import com.siireportes.objetosnegocio.Equipo;
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
 * Class EquiposJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class EquiposJpaController implements Serializable {
    
    private EntityManagerFactory emf = null;
    
    public EquiposJpaController() {
        emf = Persistence.createEntityManagerFactory("SIIRPersistenciaPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipo equipos) throws PreexistingEntityException, Exception {
        if (equipos.getSolicitudCollection() == null) {
            equipos.setSolicitudCollection(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Solicitud> attachedSolicitudesCollection = new ArrayList<Solicitud>();
            for (Solicitud solicitudesCollectionSolicitudesToAttach : equipos.getSolicitudCollection()) {
                solicitudesCollectionSolicitudesToAttach = em.getReference(solicitudesCollectionSolicitudesToAttach.getClass(), solicitudesCollectionSolicitudesToAttach.getFolio());
                attachedSolicitudesCollection.add(solicitudesCollectionSolicitudesToAttach);
            }
            equipos.setSolicitudCollection(attachedSolicitudesCollection);
            em.persist(equipos);
            for (Solicitud solicitudesCollectionSolicitudes : equipos.getSolicitudCollection()) {
                Equipo oldEquipoOfSolicitudesCollectionSolicitudes = solicitudesCollectionSolicitudes.getEquipo();
                solicitudesCollectionSolicitudes.setEquipo(equipos);
                solicitudesCollectionSolicitudes = em.merge(solicitudesCollectionSolicitudes);
                if (oldEquipoOfSolicitudesCollectionSolicitudes != null) {
                    oldEquipoOfSolicitudesCollectionSolicitudes.getSolicitudCollection().remove(solicitudesCollectionSolicitudes);
                    oldEquipoOfSolicitudesCollectionSolicitudes = em.merge(oldEquipoOfSolicitudesCollectionSolicitudes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipos(equipos.getIdEquipos()) != null) {
                throw new PreexistingEntityException("Equipos " + equipos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipo equipos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo persistentEquipos = em.find(Equipo.class, equipos.getIdEquipos());
            Collection<Solicitud> solicitudesCollectionOld = persistentEquipos.getSolicitudCollection();
            Collection<Solicitud> solicitudesCollectionNew = equipos.getSolicitudCollection();
            Collection<Solicitud> attachedSolicitudesCollectionNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudesCollectionNewSolicitudesToAttach : solicitudesCollectionNew) {
                solicitudesCollectionNewSolicitudesToAttach = em.getReference(solicitudesCollectionNewSolicitudesToAttach.getClass(), solicitudesCollectionNewSolicitudesToAttach.getFolio());
                attachedSolicitudesCollectionNew.add(solicitudesCollectionNewSolicitudesToAttach);
            }
            solicitudesCollectionNew = attachedSolicitudesCollectionNew;
            equipos.setSolicitudCollection(solicitudesCollectionNew);
            equipos = em.merge(equipos);
            for (Solicitud solicitudesCollectionOldSolicitudes : solicitudesCollectionOld) {
                if (!solicitudesCollectionNew.contains(solicitudesCollectionOldSolicitudes)) {
                    solicitudesCollectionOldSolicitudes.setEquipo(null);
                    solicitudesCollectionOldSolicitudes = em.merge(solicitudesCollectionOldSolicitudes);
                }
            }
            for (Solicitud solicitudesCollectionNewSolicitudes : solicitudesCollectionNew) {
                if (!solicitudesCollectionOld.contains(solicitudesCollectionNewSolicitudes)) {
                    Equipo oldEquipoOfSolicitudesCollectionNewSolicitudes = solicitudesCollectionNewSolicitudes.getEquipo();
                    solicitudesCollectionNewSolicitudes.setEquipo(equipos);
                    solicitudesCollectionNewSolicitudes = em.merge(solicitudesCollectionNewSolicitudes);
                    if (oldEquipoOfSolicitudesCollectionNewSolicitudes != null && !oldEquipoOfSolicitudesCollectionNewSolicitudes.equals(equipos)) {
                        oldEquipoOfSolicitudesCollectionNewSolicitudes.getSolicitudCollection().remove(solicitudesCollectionNewSolicitudes);
                        oldEquipoOfSolicitudesCollectionNewSolicitudes = em.merge(oldEquipoOfSolicitudesCollectionNewSolicitudes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = equipos.getIdEquipos();
                if (findEquipos(id) == null) {
                    throw new NonexistentEntityException("The equipos with id " + id + " no longer exists.");
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
            Equipo equipos;
            try {
                equipos = em.getReference(Equipo.class, id);
                equipos.getIdEquipos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipos with id " + id + " no longer exists.", enfe);
            }
            Collection<Solicitud> solicitudesCollection = equipos.getSolicitudCollection();
            for (Solicitud solicitudesCollectionSolicitudes : solicitudesCollection) {
                solicitudesCollectionSolicitudes.setEquipo(null);
                solicitudesCollectionSolicitudes = em.merge(solicitudesCollectionSolicitudes);
            }
            em.remove(equipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipo> findEquiposEntities() {
        return findEquiposEntities(true, -1, -1);
    }

    public List<Equipo> findEquiposEntities(int maxResults, int firstResult) {
        return findEquiposEntities(false, maxResults, firstResult);
    }

    private List<Equipo> findEquiposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipo.class));
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

    public Equipo findEquipos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquiposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipo> rt = cq.from(Equipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
