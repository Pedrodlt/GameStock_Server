/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestock.servergamestockapp.persistencia;

import com.gamestock.servergamestockapp.logica.Alquiler;
import com.gamestock.servergamestockapp.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author pedro
 */
public class AlquilerJpaController implements Serializable{
    
    public AlquilerJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gamestockPU");
    }

    public AlquilerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alquiler alquiler) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alquiler);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alquiler alquiler) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            alquiler = em.merge(alquiler);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = alquiler.getId();
                if (findAlquiler(id) == null) {
                    throw new NonexistentEntityException("The rental with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alquiler alquiler;
            try {
                alquiler = em.getReference(Alquiler.class, id);
                alquiler.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rental with id " + id + " no longer exists.", enfe);
            }
            em.remove(alquiler);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alquiler> findAlquilerEntities() {
        return findAlquilerEntities(true, -1, -1);
    }

    public List<Alquiler> findAlquilerEntities(int maxResults, int firstResult) {
        return findAlquilerEntities(false, maxResults, firstResult);
    }

    private List<Alquiler> findAlquilerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alquiler.class));
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

    public Alquiler findAlquiler(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alquiler.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlquilerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alquiler> rt = cq.from(Alquiler.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    // Método para buscar alquileres activos
    public List<Alquiler> findActiveRentals() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Alquiler> query = em.createQuery("SELECT a FROM Alquiler a WHERE a.activo = true", Alquiler.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Método para buscar alquileres por cliente
    public List<Alquiler> findRentalsByCliente(Long clienteId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Alquiler> query = em.createQuery("SELECT a FROM Alquiler a WHERE a.cliente.id = :clienteId", Alquiler.class);
            query.setParameter("clienteId", clienteId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Método para buscar alquileres por juego
    public List<Alquiler> findRentalsByJuego(Long juegoId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Alquiler> query = em.createQuery("SELECT a FROM Alquiler a WHERE a.juego.id = :juegoId", Alquiler.class);
            query.setParameter("juegoId", juegoId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}

