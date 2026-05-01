package com.backend.daos;

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
import com.backend.services.ConnectionFactory;
import com.backend.dtos.EMPRESAdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.EMPRESA;

public class EMPRESAdaoJpa implements Serializable {

    public EMPRESAdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public EMPRESAdaoJpa() {

    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(EMPRESA eEMPRESA) {
        boolean validated = true;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(eEMPRESA);
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

    public void update(EMPRESA eEMPRESA) throws NonexistentEntityException, Exception {

        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(eEMPRESA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = Long.parseLong(eEMPRESA.getEMP_CODI());
                if (findEMPRESA(id) == null) {
                    throw new NonexistentEntityException("The eEMPRESA with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(String ID_EMPRESA) throws NonexistentEntityException {
        boolean validated = false;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            EMPRESA eEMPRESA;
            try {
                eEMPRESA = findCodigo(ID_EMPRESA);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eEMPRESA with id no longer exists.", enfe);
            }
            em.remove(em.merge(eEMPRESA));
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

    public List<EMPRESA> findEMPRESAEntities() {
        return findEMPRESAEntities("", "", true, -1, -1);
    }

    public List<EMPRESA> findEMPRESAEntities(String filtro_cli_tiposgq, String filtro_cli_nome) {
        return findEMPRESAEntities(filtro_cli_tiposgq, filtro_cli_nome, true, -1, -1);
    }

    public List<EMPRESA> findEMPRESAEntities(
            String filtro_cli_tiposgq,
            String filtro_cli_nome,
            int maxResults,
            int firstResult) {
        return findEMPRESAEntities(
                filtro_cli_tiposgq,
                filtro_cli_nome,
                false,
                maxResults,
                firstResult);
    }

    private List<EMPRESA> findEMPRESAEntities(
            String filtro_cli_tiposgq,
            String filtro_cli_nome,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EMPRESA> cq = cb.createQuery(EMPRESA.class);
            Root<EMPRESA> empresa = cq.from(EMPRESA.class);
            Predicate nomePredicate = cb.like(empresa.get("EMP_RZSO"), "%" + filtro_cli_nome.trim().toUpperCase() + "%");
            Predicate notNullNome = cb.isNotNull(empresa.get("EMP_RZSO"));
            //cq.where(notNullNome);

            if (filtro_cli_tiposgq.trim().length() > 0) {
                if (!filtro_cli_tiposgq.trim().equals("TODOS")) {
                    cq.where(notNullNome);
                }
                if (filtro_cli_nome.trim().length() > 0) {
                    if (!filtro_cli_tiposgq.trim().equals("TODOS")) {
                        cq.where(notNullNome, nomePredicate);
                    } else {
                        cq.where(notNullNome, nomePredicate);
                    }
                }
            }

            cq.orderBy(cb.asc(empresa.get("CLI_NOME")), 
                    cb.asc(empresa.get("CLI_CODI")));
            TypedQuery<EMPRESA> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EMPRESA findEMPRESA(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(EMPRESA.class, id);
        } finally {
            em.close();
        }
    }

    public int getEMPRESACount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EMPRESA> rt = cq.from(EMPRESA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static EMPRESA findCodigo(@NotNull final String empcodi) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<EMPRESA> typed = entityManager
                    .createNamedQuery("EMPRESA.findByEmpCodi", EMPRESA.class)
                    .setParameter("EMP_CODI", empcodi);
            return typed.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }
    
     public static EMPRESA findLastCliCodi() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<EMPRESA> typed = entityManager
                    .createNamedQuery("EMPRESA.findLastCliCodi", EMPRESA.class);
            return typed.getResultList().get(0);            
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static EMPRESA findCnpj(@NotNull final String empcnpj) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<EMPRESA> typed = entityManager
                    .createNamedQuery("EMPRESA.findByCliCgc0", EMPRESA.class)
                    .setParameter("CLI_CGC0", empcnpj);
            return typed.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(EMPRESAdto classe) throws Exception {
        boolean wValida = false;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
        Set<ConstraintViolation<EMPRESAdto>> constraintViolations
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
