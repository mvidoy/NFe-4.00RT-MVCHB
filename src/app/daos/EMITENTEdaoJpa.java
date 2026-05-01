/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.daos;

import app.controllers.exceptions.NonexistentEntityException;
import app.models.EMITENTE;
import com.backend.services.ConnectionFactory;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Osvaldo Vidoy
 */
public class EMITENTEdaoJpa implements Serializable {

    public EMITENTEdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public EMITENTEdaoJpa() {

    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EMITENTE eMITENTE) {
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(eMITENTE);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EMITENTE eMITENTE) throws NonexistentEntityException, Exception {
       EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();

            EMITENTE rEMITENTE = findCodigo(eMITENTE.getEMP_CODI());
         
            if (eMITENTE.getEMP_CERTIFICADO() == null) {
                eMITENTE.setEMP_CERTIFICADO(rEMITENTE.getEMP_CERTIFICADO());
                eMITENTE.setEMP_SENHACERTIFICADO(rEMITENTE.getEMP_SENHACERTIFICADO());
            }
            
            eMITENTE = em.merge(eMITENTE);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = Long.parseLong(eMITENTE.getEMP_CODI());
                if (findEMITENTE(id) == null) {
                    throw new NonexistentEntityException("The eMITENTE with id " + id + " no longer exists.");
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
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            EMITENTE eMITENTE;
            try {
                eMITENTE = em.getReference(EMITENTE.class, id);
                //eMITENTE.getEmpCodi();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eMITENTE with id " + id + " no longer exists.", enfe);
            }
            em.remove(eMITENTE);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EMITENTE> findEMITENTEEntities() {
        return findEMITENTEEntities(true, -1, -1);
    }

    public List<EMITENTE> findEMITENTEEntities(int maxResults, int firstResult) {
        return findEMITENTEEntities(false, maxResults, firstResult);
    }

    private List<EMITENTE> findEMITENTEEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EMITENTE.class));
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

    public EMITENTE findEMITENTE(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(EMITENTE.class, id);
        } finally {
            em.close();
        }
    }

    public int getEMITENTECount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EMITENTE> rt = cq.from(EMITENTE.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static EMITENTE findCodigo(@NotNull final String empcodi) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<EMITENTE> typed = entityManager
                    .createNamedQuery("EMITENTE.findByEmpCodi", EMITENTE.class)
                    .setParameter("EMP_CODI", empcodi);
            return typed.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public static EMITENTE findCnpj(@NotNull final String empcnpj) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<EMITENTE> typed = entityManager
                    .createNamedQuery("EMITENTE.findByEmpCgc0", EMITENTE.class)
                    .setParameter("EMP_CGC0", empcnpj);
            return typed.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

}
