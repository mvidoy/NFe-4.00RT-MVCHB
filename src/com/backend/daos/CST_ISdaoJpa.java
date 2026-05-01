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
import java.text.ParseException;
import java.util.ArrayList;
import app.exceptions.NonexistentEntityException;
import app.models.CST_IS;
import com.backend.dtos.CST_ISdto;
import com.backend.services.ConnectionFactory;

public class CST_ISdaoJpa implements Serializable {

    public CST_ISdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public CST_ISdaoJpa() {

    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(CST_IS cST_IS) {
        boolean validated = true;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(cST_IS);
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

    public void update(CST_IS cST_IS) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(cST_IS);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = Long.parseLong(cST_IS.getCST_CODIGO());
                if (findCST_IS(id) == null) {
                    throw new NonexistentEntityException("The CST_IS with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(String CST_CODIGO) throws NonexistentEntityException {
        boolean validated = false;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            CST_IS cST_IS;
            try {
                cST_IS = NamedQueryFindByCodigo(CST_CODIGO);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CST_IS with id no longer exists.", enfe);
            }
            em.remove(em.merge(cST_IS));
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

    public List<CST_IS> findCST_ISEntities() {
        return findCST_ISEntities("", true, -1, -1);
    }

    public List<CST_IS> findCST_ISEntities(String filtro_CST_DESCRICAO) {
        return findCST_ISEntities(filtro_CST_DESCRICAO, true, -1, -1);
    }

    public List<CST_IS> findCST_ISEntities(
            String filtro_CST_DESCRICAO,
            int maxResults,
            int firstResult) {
        return findCST_ISEntities(
                filtro_CST_DESCRICAO,
                false,
                maxResults,
                firstResult);
    }

    private List<CST_IS> findCST_ISEntities(
            String filtro_CST_DESCRICAO,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CST_IS> cq = cb.createQuery(CST_IS.class);
            Root<CST_IS> grupo = cq.from(CST_IS.class);
            Predicate descPredicate = cb.like(grupo.get("CST_DESCRICAO"), "%" + filtro_CST_DESCRICAO.trim().toUpperCase() + "%");
            Predicate notNullDesc = cb.isNotNull(grupo.get("CST_DESCRICAO"));
            if (filtro_CST_DESCRICAO.trim().length() > 0) {
                cq.where(notNullDesc, descPredicate);
            }
            cq.orderBy(cb.asc(grupo.get("CST_DESCRICAO")),
                    cb.asc(grupo.get("CST_CODIGO")));
            TypedQuery<CST_IS> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CST_IS findCST_IS(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(CST_IS.class, id);
        } finally {
            em.close();
        }
    }

    public int getCST_ISCount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CST_IS> rt = cq.from(CST_IS.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static CST_IS NamedQueryFindByCodigo(@NotNull final String cST_CODIGO) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CST_IS> typed = entityManager
                    .createNamedQuery("CST_IS.NamedQueryFindByCodigo", CST_IS.class)
                    .setParameter("CST_CODIGO", cST_CODIGO);
            return typed.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }
    
    public static CST_IS NamedQueryFindLastCodigo() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CST_IS> typed = entityManager
                    .createNamedQuery("CST_IS.NamedQueryFindLastCodigo", CST_IS.class);
            return typed.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static CST_IS NamedQueryFindByCodigo() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CST_IS> typed = entityManager
                    .createNamedQuery("CST_IS.NamedQueryFindByCodigo", CST_IS.class);
            return typed.getResultList().get(0);
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<CST_ISdto> NamedQueryFindAll(String filtro_CST_DESCRICAO) throws ParseException {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CST_IS> typed = entityManager
                    .createNamedQuery("CST_IS.NamedQueryFindAll", CST_IS.class);
            //.setParameter("CST_DESCRICAO", "");
            if (filtro_CST_DESCRICAO.trim().length() > 0) {
                typed = entityManager
                        .createNamedQuery("CST_IS.NamedQueryFindAllLike", CST_IS.class)
                        .setParameter("CST_DESCRICAO", filtro_CST_DESCRICAO);
            }
            List<CST_ISdto> ListNFEdto = new ArrayList<CST_ISdto>();
            List<CST_IS> lst = typed.getResultList();
            for (CST_IS grupo : lst) {
                CST_ISdto nFEdto = CST_ISdto.convertCST_IStoCST_ISdto(grupo);
                ListNFEdto.add(nFEdto);
            }
            return ListNFEdto;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(CST_ISdto classe) throws Exception {
        boolean wValida = false;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
        Set<ConstraintViolation<CST_ISdto>> constraintViolations
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
