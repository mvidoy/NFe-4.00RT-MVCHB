package com.backend.daos;

import com.backend.dtos.CANFEIRDUPLdto;
import static com.backend.dtos.CANFEIRDUPLdto.convertCANFEIRDUPLdtotoCANFEIRDUPL;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.services.ConnectionFactory;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
import java.util.ArrayList;
import javax.persistence.criteria.Selection;
import com.backend.models.CANFEIRDUPL;
import java.text.ParseException;

public class CANFEIRDUPLdaoJpa implements Serializable {

    private final List<Selection<?>> projection = new ArrayList<>();

    public CANFEIRDUPLdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CANFEIRDUPLdaoJpa() {

    }

    public boolean create(CANFEIRDUPL caNFEIRDUPL) {
        boolean validated = true;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(caNFEIRDUPL);
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

    public void update(CANFEIRDUPL caNFEIRDUPL) throws NonexistentEntityException, Exception {

        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(caNFEIRDUPL);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static boolean delete(String IRE_CODI) throws NonexistentEntityException, ParseException {
        boolean validated = false;
        EntityManager em = null;
        em = new ConnectionFactory().getConnection();
        List<CANFEIRDUPLdto> caNFEIRDUPLdto = NamedQueryFindAllNota(IRE_CODI);
        for (int i = 0; i < caNFEIRDUPLdto.size(); i++) {
            CANFEIRDUPL caNFEIRDUPL = new CANFEIRDUPL();
            caNFEIRDUPL = convertCANFEIRDUPLdtotoCANFEIRDUPL(caNFEIRDUPLdto.get(i));
            em.getTransaction().begin();
            em.remove(em.merge(caNFEIRDUPL));
            em.getTransaction().commit();
        }
        validated = true;
        if (em != null) {
            em.close();
        }
        return validated;
    }

    public List<CANFEIRDUPLdto> findCANFEIRDUPLEntities() {
        return findCANFEIRDUPLEntities("", true, -1, -1);
    }

    public List<CANFEIRDUPLdto> findCANFEIRDUPLEntities(String ire_codi, String ire_parc) {
        return findCANFEIRDUPLEntities(ire_parc, true, -1, -1);
    }

    public List<CANFEIRDUPLdto> findCANFEIRDUPLEntities(
            String ire_parc,
            int maxResults,
            int firstResult) {
        return findCANFEIRDUPLEntities(
                ire_parc,
                false,
                maxResults,
                firstResult);
    }

    private List<CANFEIRDUPLdto> findCANFEIRDUPLEntities(
            String ire_parc,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CANFEIRDUPLdto> cq = cb.createQuery(CANFEIRDUPLdto.class);
            Root<CANFEIRDUPL> caNFEIRDUPL = cq.from(CANFEIRDUPL.class);
            Predicate ire_parcPredicate = cb.equal(caNFEIRDUPL.get("IRE_CODI"), ire_parc.trim());
            cq.where(ire_parcPredicate);
            cq.orderBy(cb.asc(caNFEIRDUPL.get("IRE_CODI")),
                    cb.asc(caNFEIRDUPL.get("IRE_PARC")));
            TypedQuery<CANFEIRDUPLdto> q = em.createQuery(cq);
            List<CANFEIRDUPLdto> ListCANFEIRDUPL = q.getResultList();
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<CANFEIRDUPL> NamedQueryListByNota(@NotNull final String ire_codi) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEIRDUPL> typed = entityManager
                    .createNamedQuery("CANFEIRDUPL.NamedQueryListByNota", CANFEIRDUPL.class)
                    .setParameter("IRE_CODI", ire_codi);
            return typed.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public CANFEIRDUPL findCANFEIRDUPL(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(CANFEIRDUPL.class, id);
        } finally {
            em.close();
        }
    }

    public int getCANFEIRDUPLCount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CANFEIRDUPL> rt = cq.from(CANFEIRDUPL.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static CANFEIRDUPL NamedQueryFindByNotaParc(@NotNull final String ire_codi, final String ire_parc) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEIRDUPL> typed = entityManager
                    .createNamedQuery("CANFEIRDUPL.NamedQueryFindByNotaParc", CANFEIRDUPL.class)
                    .setParameter("IRE_CODI", ire_codi)
                    .setParameter("IRE_PARC", ire_parc);
            return typed.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static CANFEIRDUPL findByLastParCodi() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEIRDUPL> typed = entityManager
                    .createNamedQuery("CANFEIRDUPL.findByLastParCodi", CANFEIRDUPL.class);
            return typed.getResultList().get(0);
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<CANFEIRDUPLdto> NamedQueryFindAllNota(String IRE_CODI) throws ParseException {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEIRDUPL> typed = entityManager
                    .createNamedQuery("CANFEIRDUPL.NamedQueryFindAllNota", CANFEIRDUPL.class)
                    .setParameter("IRE_CODI", IRE_CODI);
            List<CANFEIRDUPLdto> ListCANFEIRDUPLdto = new ArrayList<CANFEIRDUPLdto>();
            List<CANFEIRDUPL> lst = typed.getResultList();

            for (CANFEIRDUPL m2iteped : lst) {
                CANFEIRDUPLdto caNFEIRDUPLdto = CANFEIRDUPLdto.convertCANFEIRDUPLtoCANFEIRDUPLdto(m2iteped);
                ListCANFEIRDUPLdto.add(caNFEIRDUPLdto);
            }
            return ListCANFEIRDUPLdto;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(CANFEIRDUPLdto classe) throws Exception {
        boolean wValida = false;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
        Set<ConstraintViolation<CANFEIRDUPLdto>> constraintViolations
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
