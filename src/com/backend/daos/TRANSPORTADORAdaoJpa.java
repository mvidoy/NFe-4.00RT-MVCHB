package com.backend.daos;

import com.backend.dtos.TRANSPORTADORAdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.TRANSPORTADORA;
import com.backend.services.ConnectionFactory;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.metamodel.EntityType;

public class TRANSPORTADORAdaoJpa implements Serializable {

    public TRANSPORTADORAdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public TRANSPORTADORAdaoJpa() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(TRANSPORTADORA tRANSPORTADORA) {
        boolean validated = true;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(tRANSPORTADORA);
            em.getTransaction().commit();
        } catch (Exception e) {
            validated = false;
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return validated;
    }

    public void update(TRANSPORTADORA tRANSPORTADORA) throws NonexistentEntityException, Exception {

        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(tRANSPORTADORA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tRANSPORTADORA.getOid();
                if (findTRANSPORTADORA(id) == null) {
                    throw new NonexistentEntityException("The TRANSPORTADORA with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(String TRA_CODI) throws NonexistentEntityException {
        boolean validated = false;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            TRANSPORTADORA tRANSPORTADORA;
            try {
                tRANSPORTADORA = findCodigo(TRA_CODI);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TRANSPORTADORA with id no longer exists.", enfe);
            }
            em.remove(em.merge(tRANSPORTADORA));
            em.getTransaction().commit();
            validated = true;
        } catch (NonexistentEntityException e) {
            validated = false;
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return validated;
    }

    public List<TRANSPORTADORA> findTRANSPORTADORAEntities() {
        return findTRANSPORTADORAEntities("", true, -1, -1);
    }

    public List<TRANSPORTADORA> findTRANSPORTADORAEntities(String filtro_tra_desc) {
        return findTRANSPORTADORAEntities(filtro_tra_desc, true, -1, -1);
    }

    public List<TRANSPORTADORA> findTRANSPORTADORAEntities(
            String filtro_tra_desc,
            int maxResults,
            int firstResult) {
        return findTRANSPORTADORAEntities(
                filtro_tra_desc,
                false,
                maxResults,
                firstResult);
    }

    public List<TRANSPORTADORAdto> findTRANSPORTADORAEntities(
            String filtro_tra_tipo,
            String filtro_tra_desc,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TRANSPORTADORA> cq = cb.createQuery(TRANSPORTADORA.class);
            Root<TRANSPORTADORA> transportadora = cq.from(TRANSPORTADORA.class);
            Predicate descPredicate = cb.like(transportadora.get("TRA_DESC"), "%" + filtro_tra_desc.trim().toUpperCase() + "%");
            Predicate notNullDesc = cb.isNotNull(transportadora.get("TRA_DESC"));
            cq.where(notNullDesc);
            if (filtro_tra_desc.trim().length() > 0) {
                cq.where(notNullDesc, descPredicate);
            }

            cq.orderBy(cb.asc(transportadora.get("TRA_DESC")),
                    cb.asc(transportadora.get("TRA_CODI")));

            TypedQuery<TRANSPORTADORA> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<TRANSPORTADORAdto> ListTRANSPORTADORAdto = new ArrayList<TRANSPORTADORAdto>();
            List<TRANSPORTADORA> lst = q.getResultList();

            for (TRANSPORTADORA tra : lst) {
                TRANSPORTADORAdto tRANSPORTADORAdto = TRANSPORTADORAdto.convertTRANSPORTADORAtoTRANSPORTADORAdto(tra);
                ListTRANSPORTADORAdto.add(tRANSPORTADORAdto);
            }

            return ListTRANSPORTADORAdto;
        } catch (ParseException ex) {
            Logger.getLogger(TRANSPORTADORAdaoJpa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return null;
    }

    private List<TRANSPORTADORA> findTRANSPORTADORAEntities(
            String filtro_tra_desc,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TRANSPORTADORA> cq = cb.createQuery(TRANSPORTADORA.class);
            Root<TRANSPORTADORA> transportadora = cq.from(TRANSPORTADORA.class);
            Predicate descPredicate = cb.like(transportadora.get("TRA_DESC"), "%" + filtro_tra_desc.trim().toUpperCase() + "%");
            Predicate notNullDesc = cb.isNotNull(transportadora.get("TRA_DESC"));
            cq.where(notNullDesc);
            if (filtro_tra_desc.trim().length() > 0) {
                cq.where(notNullDesc, descPredicate);
            }

            cq.orderBy(cb.asc(transportadora.get("TRA_DESC")),
                    cb.asc(transportadora.get("TRA_CODI")));

            TypedQuery<TRANSPORTADORA> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TRANSPORTADORA findTRANSPORTADORA(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(TRANSPORTADORA.class, id);
        } finally {
            em.close();
        }
    }

    public int getTRANSPORTADORACount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TRANSPORTADORA> rt = cq.from(TRANSPORTADORA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static TRANSPORTADORA findCodigo(@NotNull final String tra_codi) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<TRANSPORTADORA> typed = entityManager
                    .createNamedQuery("TRANSPORTADORA.findByTraCodi", TRANSPORTADORA.class)
                    .setParameter("TRA_CODI", tra_codi);
            return typed.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static TRANSPORTADORA findLastTraCodi() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<TRANSPORTADORA> typed = entityManager
                    .createNamedQuery("TRANSPORTADORA.findLastTraCodi", TRANSPORTADORA.class);
            return typed.getResultList().get(0);
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(TRANSPORTADORAdto classe) throws Exception {
        boolean wValida = false;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
        Set<ConstraintViolation<TRANSPORTADORAdto>> constraintViolations
                = validator.validate(classe);
        String msgError = "";
        System.setProperty("MsgInvalid", msgError);
        for (ConstraintViolation error : constraintViolations) {
            msgError += error.getMessage() + "\n";
        }
        System.setProperty("MsgInvalid", msgError);
        if (msgError.length() > 0) {
            wValida = true;
        }
        return wValida;
    }
}
