package com.backend.daos;

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
import java.util.ArrayList;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import com.backend.services.ConnectionFactory;
import com.backend.dtos.NFEDETPRODdto;
import com.backend.dtos.NFEdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.NFE;
import com.backend.models.NFECB;
import com.backend.models.NFEDETPROD;
import java.text.ParseException;
import java.util.Collections;

public class NFEdaoJpa implements Serializable {

    public NFEdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public NFEdaoJpa() {

    }

    public void update(NFE nFE) throws NonexistentEntityException, Exception {

        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(nFE);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<NFE> findNFEhbEntities() {
        return findNFEhbEntities("", true, -1, -1);
    }

    public List<NFE> findNFEhbEntities(String filtro_dest_cnpj) {
        return findNFEhbEntities(filtro_dest_cnpj, true, -1, -1);
    }

    public List<NFE> findNFEhbEntities(
            String filtro_dest_cnpj,
            int maxResults,
            int firstResult) {
        return findNFEhbEntities(
                filtro_dest_cnpj,
                false,
                maxResults,
                firstResult);
    }

    private List<NFE> findNFEhbEntities(
            String filtro_dest_cnpj,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<NFE> cq = cb.createQuery(NFE.class);
            Root<NFE> nfe = cq.from(NFE.class);
            Predicate cnpjPredicate = cb.equal(nfe.get("DEST_CNPJ"), filtro_dest_cnpj.trim());
            Predicate notNullNome = cb.isNotNull(nfe.get("INFNFEhb_STATUSNFEhb"));
            if (filtro_dest_cnpj.trim().length() > 0) {
                cq.where(notNullNome, cnpjPredicate);
            }

            cq.orderBy(cb.asc(nfe.get("IDE_NNF")));

            TypedQuery<NFE> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NFE findNFEhb(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(NFE.class, id);
        } finally {
            em.close();
        }
    }

    public int getNFEhbCount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NFE> rt = cq.from(NFE.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static List<NFEdto> NamedQueryFindAllByDestCnpj(int pageSize, int currentPage, @NotNull final String destcnpj, @NotNull final Integer idetpnf) throws ParseException {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<NFE> typed = entityManager
                    .createNamedQuery("NFEhb.NamedQueryFindAllByDestCnpj", NFE.class)
                    .setParameter("DEST_CNPJ", destcnpj)
                    .setParameter("IDE_TPNF", idetpnf);
            int totalRegistros = typed.getResultList().size() + pageSize;
            NFEtotalRegistrosSingleton.getInstance().setTotalRegistros(totalRegistros);
            if (totalRegistros == 0) {
                return Collections.emptyList();
            }
            currentPage = currentPage == 0 ? (totalRegistros / pageSize) : currentPage;
            currentPage = (currentPage > 1) ? currentPage : 1;
            int FirstResult = (currentPage - 1) * pageSize;
            if (FirstResult > totalRegistros) {
                FirstResult = totalRegistros - pageSize;
            }
            typed.setFirstResult(FirstResult);
            typed.setMaxResults(pageSize);
            if (typed.getResultList().size() == 0
                    && totalRegistros > pageSize) {
                FirstResult = totalRegistros - (pageSize * 2);
                if (FirstResult <= 0) {
                    FirstResult = 0;
                }
                typed.setFirstResult(FirstResult);
                typed.setMaxResults(pageSize);
            }
            List<NFEdto> ListNFEdto = new ArrayList<NFEdto>();
            List<NFE> lst = typed.getResultList();
            for (NFE o : lst) {
                NFEdto nFEdto = NFEdto.convertNFEtoNFEdto(o);
                ListNFEdto.add(nFEdto);
            }
            return ListNFEdto;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static NFEdto NamedQueryFindByIDENNF(@NotNull final Integer ide_nnf) throws ParseException {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<NFE> query = entityManager
                    .createNamedQuery("NFE.NamedQueryFindByIDENNF", NFE.class)
                    .setParameter("IDE_NNF", ide_nnf);

            NFE nfe = query.getSingleResult();  // Espera 1 único resultado

            if (nfe == null) {
                return null;
            }

            return NFEdto.convertNFEtoNFEdto(nfe); // Não chama setNFEDETPRODdto
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<NFEDETPRODdto> NamedQueryFindByDetProdNNF(@NotNull final Integer ide_nnf) throws ParseException {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<NFEDETPROD> typed = entityManager
                    .createNamedQuery("NFEDETPROD.NamedQueryFindByDetProdNNF", NFEDETPROD.class)
                    .setParameter("DET_PROD_NNF", ide_nnf);

            if (typed.getResultList() == null) {
                return null;
            }

            List<NFEDETPROD> lstNFEDETPROD = typed.getResultList();
            if (lstNFEDETPROD.isEmpty()) {
                return null;
            }
            return NFEDETPRODdto.convertListNFEDETPRODtoListNFEDETPRODdto(lstNFEDETPROD);
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(NFEdto classe) throws Exception {
        boolean wValida = false;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
        Set<ConstraintViolation<NFEdto>> constraintViolations
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

    public static class NFEtotalRegistrosSingleton {

        private static NFEtotalRegistrosSingleton instance;
        private int totalRegistros;

        private NFEtotalRegistrosSingleton() {
            // Construtor privado para evitar instanciação direta
        }

        public static NFEtotalRegistrosSingleton getInstance() {
            if (instance == null) {
                instance = new NFEtotalRegistrosSingleton();
            }
            return instance;
        }

        public int getTotalRegistros() {
            return totalRegistros;
        }

        public void setTotalRegistros(int totalRegistros) {
            this.totalRegistros = totalRegistros;
        }
    }

    public NFECB NamedQueryCBFindByIDENNF(@NotNull final String ide_nnf) {
        EntityManager entityManager = new ConnectionFactory().getConnection();

        try {
            TypedQuery<NFECB> typed = entityManager
                    .createNamedQuery("NFECB.NamedQueryCBFindByIDENNF", NFECB.class)
                    .setParameter("IDE_NNF", ide_nnf);

            List<NFECB> results = typed.getResultList();

            if (results == null || results.isEmpty()) {
                return null;
            }

            return results.get(0);

        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public void update(NFECB nFE) throws NonexistentEntityException, Exception {

        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(nFE);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
