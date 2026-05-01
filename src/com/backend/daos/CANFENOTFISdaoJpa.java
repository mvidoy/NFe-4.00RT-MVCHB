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
import com.backend.dtos.CANFENOTFISdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.CANFEDUPLIC;
import com.backend.models.CANFENOTFIS;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import org.hibernate.Hibernate;

public class CANFENOTFISdaoJpa implements Serializable {

    public static int CANFENOTFISRecordCount; //novo

    public static int getCANFENOTFISRecordCount() {
        return CANFENOTFISRecordCount;
    }

    public static void setCANFENOTFISRecordCount(int aCANFENOTFISRecordCount) {
        CANFENOTFISRecordCount = aCANFENOTFISRecordCount;
    }

    public CANFENOTFISdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CANFENOTFISdaoJpa() {

    }

    public boolean create(CANFENOTFIS caNFENOTFIS) {
        boolean validated = true;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(caNFENOTFIS);
            
            //if (caNFENOTFIS.getCANFEDUPLIC() != null) {
             //   for (CANFEDUPLIC canfeduplic : caNFENOTFIS.getCANFEDUPLIC()) {
                   // canfeduplic.setCANFENOTFIS_Id(caNFENOTFIS.getId());
            //        em.persist(canfeduplic);
            //    }
            //}
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

    public void update(CANFENOTFIS caNFENOTFIS) throws NonexistentEntityException, Exception {

        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(caNFENOTFIS);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = Long.parseLong(caNFENOTFIS.getNOT_NOTA());
                if (findCANFENOTFIS(id) == null) {
                    throw new NonexistentEntityException("The caNFENOTFIS with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(String ID_NOT_NOTA) throws NonexistentEntityException {
        boolean validated = false;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            CANFENOTFIS caNFENOTFIS;
            try {
                caNFENOTFIS = NamedQueryFindByNota(ID_NOT_NOTA);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caNFENOTFIS with id no longer exists.", enfe);
            }
            em.remove(em.merge(caNFENOTFIS));
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

    public List<CANFENOTFIS> findCANFENOTFISEntities() {
        return findCANFENOTFISEntities("", true, -1, -1);
    }

    public List<CANFENOTFIS> findCANFENOTFISEntities(String not_clie) {
        return findCANFENOTFISEntities(not_clie, true, -1, -1);
    }

    public List<CANFENOTFIS> findCANFENOTFISEntities(
            String not_clie,
            int maxResults,
            int firstResult) {
        return findCANFENOTFISEntities(
                not_clie,
                false,
                maxResults,
                firstResult);
    }

    private List<CANFENOTFIS> findCANFENOTFISEntities(
            String not_clie,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CANFENOTFIS> cq = cb.createQuery(CANFENOTFIS.class);
            Root<CANFENOTFIS> caNFENOTFIS = cq.from(CANFENOTFIS.class);
            Predicate not_cliePredicate = cb.equal(caNFENOTFIS.get("NOT_CLIE"), not_clie.trim());
            Predicate epr_situPredicate = cb.notEqual(caNFENOTFIS.get("NOT_SITU"), "S");
            Predicate epr_dtemPredicate = cb.isNotNull(caNFENOTFIS.get("NOT_DTEM"));

            cq.where(not_cliePredicate,
                    epr_situPredicate,
                    epr_dtemPredicate);

            cq.orderBy(cb.asc(caNFENOTFIS.get("NOT_DTEM")),
                    cb.asc(caNFENOTFIS.get("NOT_CLIE")),
                    cb.asc(caNFENOTFIS.get("NOT_NOTA")));

            TypedQuery<CANFENOTFIS> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Object[]> JpqlListByClieNota(@NotNull final String not_clie) {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            String jpql = "SELECT EPR "
                    + "FROM CANFENOTFIS EPR, CANFEITENOT INF "
                    + "RIGHT JOIN CANFEITENOT INF ON "
                    + "INF.INF_CLIE = NOT.NOT_CLIE AND "
                    + "INF.INF_NOTA = NOT.NOT_NOTA "
                    + "WHERE "
                    //+ "INF.INF_CLIE = NOT.NOT_CLIE AND "
                    //+ "INF.INF_NOTA = NOT.NOT_NOTA AND "
                    + "NOT.NOT_CLIE = '00867' AND "
                    + "NOT.NOT_SITU <> 'S' AND "
                    + "to_char(NOT.NOT_DTEM,'YYYY/MM/DD') <> '' "
                    + "ORDER BY NOT.NOT_DTEM asc, NOT.NOT_NOTA asc";

            String jpql2 = "SELECT EPR, INF FROM CANFENOTFIS EPR "
                    + "JOIN FETCH CANFEITENOT INF ON "
                    + "INF.INF_CLIE = NOT.NOT_CLIE AND "
                    + "INF.INF_NOTA = NOT.NOT_NOTA "
                    + "WHERE NOT.NOT_CLIE = '00867' AND "
                    + "NOT.NOT_SITU <> 'S' AND "
                    + "to_char(NOT.NOT_DTEM,'YYYY/MM/DD') <> '' "
                    + "ORDER BY NOT.NOT_DTEM asc, NOT.NOT_NOTA asc";

            TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql2, Object[].class);

            List<Object[]> lst = typedQuery.getResultList();
            return lst;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public CANFENOTFIS findCANFENOTFIS(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(CANFENOTFIS.class, id);
        } finally {
            em.close();
        }
    }

    public int getCANFENOTFISCount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CANFENOTFIS> rt = cq.from(CANFENOTFIS.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static CANFENOTFIS NamedQueryFindByNota(@NotNull final String not_nota) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFENOTFIS> typed = entityManager
                    .createNamedQuery("CANFENOTFIS.NamedQueryFindByNota", CANFENOTFIS.class)
                    .setParameter("NOT_NOTA", not_nota);
            CANFENOTFIS caNFENOTFIS = new CANFENOTFIS();
            CANFEDUPLIC caNFEDUPLIC = new CANFEDUPLIC();
            NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
            caNFENOTFIS = typed.getSingleResult();
            //caNFENOTFIS.setNFE(nFEdaoJpa.NamedQueryFindByIDENNF(not_nota)); //09/10/2025
            
            //if (caNFENOTFIS.getCANFEDUPLIC() != null) {
           //     Hibernate.initialize(caNFENOTFIS.getCANFEDUPLIC());
           // }
            return caNFENOTFIS;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    
    public static CANFENOTFIS NamedQueryFindByLastNota() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFENOTFIS> typed = entityManager
                    .createNamedQuery("CANFENOTFIS.NamedQueryFindByLastNota", CANFENOTFIS.class)
                    .setMaxResults(1);
            CANFENOTFIS caNFENOTFIS = new CANFENOTFIS();
            NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
            caNFENOTFIS = typed.getResultList().get(0);
            
            //if (caNFENOTFIS.getCANFEDUPLIC() != null) {
            //    Hibernate.initialize(caNFENOTFIS.getCANFEDUPLIC());
            //}
            return caNFENOTFIS;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<CANFENOTFISdto> NamedQueryFindAll(int pageSize, int pageNumber) throws ParseException, SQLException {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFENOTFIS> typed = entityManager
                    .createNamedQuery("CANFENOTFIS.NamedQueryFindAll", CANFENOTFIS.class);

            CANFENOTFISdaoJpa.CANFENOTFISRecordCount = (int) NamedQueryCountFindAll(); //novo

            int FirstResult = pageSize * pageNumber;
            if (FirstResult > CANFENOTFISdaoJpa.CANFENOTFISRecordCount) {
                FirstResult = CANFENOTFISdaoJpa.CANFENOTFISRecordCount / pageSize;
            }
            typed.setFirstResult(FirstResult);
            typed.setMaxResults(pageSize);

            List<CANFENOTFISdto> ListCANFENOTFISdto = new ArrayList<CANFENOTFISdto>();
            List<CANFENOTFIS> lst = typed.getResultList();
            for (CANFENOTFIS caNFENOTFIS : lst) {
                //if (caNFENOTFIS.getCLIENTE() != null) {
                //    CANFENOTFISdto caNFENOTFISdto = CANFENOTFISdto.convertCANFENOTFIStoCANFENOTFISdto(caNFENOTFIS);
                //    NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
                //    caNFENOTFISdto.setNFEdto(nFEdaoJpa.NamedQueryFindByIDENNF(caNFENOTFISdto.getNOT_NOTA()));
                //    ListCANFENOTFISdto.add(caNFENOTFISdto);
                //}
            }
            return ListCANFENOTFISdto;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<CANFENOTFISdto> NamedQueryFindAll(String filtro_CLI_NOME, int pageSize, int pageNumber) throws ParseException, SQLException {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<Object[]> typed = entityManager
                    .createNamedQuery("CANFENOTFIS.NamedQueryFindAllLike", Object[].class)
                    .setParameter("CLI_NOME", filtro_CLI_NOME);

            if (filtro_CLI_NOME.trim().length() > 0) {
                CANFENOTFISdaoJpa.CANFENOTFISRecordCount = (int) NamedQueryCountFindAllLike(filtro_CLI_NOME); //novo
            } else {
                CANFENOTFISdaoJpa.CANFENOTFISRecordCount = (int) NamedQueryCountFindAll(); //novo
            }

            int FirstResult = pageSize * pageNumber;
            if (FirstResult > CANFENOTFISdaoJpa.CANFENOTFISRecordCount) {
                FirstResult = CANFENOTFISdaoJpa.CANFENOTFISRecordCount / pageSize;
            }

            typed.setFirstResult(FirstResult);
            typed.setMaxResults(pageSize);
            //

            List<CANFENOTFISdto> ListCANFENOTFISdto = new ArrayList<CANFENOTFISdto>();
            List<Object[]> lst = typed.getResultList();

            for (Object o[] : lst) {
                CANFENOTFISdto caNFENOTFISdto = CANFENOTFISdto.convertCANFENOTFIStoCANFENOTFISdto((CANFENOTFIS) o[0]);
                //CLIENTEdto cLINFTEdto = CLIENTEdto.convertCLIENTEtoCLIENTEdto((CLIENTE) o[1], true);
                //List<CLIENTEdto> ListCLIENTEdto = new ArrayList<CLIENTEdto>();
                //ListCLIENTEdto.add(cLINFTEdto);
                //cLINFTEdto.setCLIENTEdto(ListCLIENTEdto);
                //caNFENOTFISdto.setCLIENTEdto(cLINFTEdto);
                NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
                //caNFENOTFISdto.setNFEdto(nFEdaoJpa.NamedQueryFindByIDENNF(caNFENOTFISdto.getNOT_NOTA()));
                ListCANFENOTFISdto.add(caNFENOTFISdto);
            }
            return ListCANFENOTFISdto;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(CANFENOTFISdto classe) throws Exception {
        boolean wValida = false;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
        Set<ConstraintViolation<CANFENOTFISdto>> constraintViolations
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

    public static class CANFENOTFIStotalRegistrosSingleton {

        private static CANFENOTFIStotalRegistrosSingleton instance;
        private int totalRegistros;

        private CANFENOTFIStotalRegistrosSingleton() {
            // Construtor privado para evitar instanciação direta
        }

        public static CANFENOTFIStotalRegistrosSingleton getInstance() {
            if (instance == null) {
                instance = new CANFENOTFIStotalRegistrosSingleton();
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

    public static long NamedQueryCountFindAll() throws ParseException, SQLException {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<Long> typed = entityManager
                    .createNamedQuery("CANFENOTFIS.NamedQueryCountFindAll", Long.class);
            long regCount = typed.getResultList().size();
            return regCount;
        } finally {
            entityManager.close();
        }
    }

    public static long NamedQueryCountFindAllLike(String filtro_CLI_NOME) throws ParseException, SQLException {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<Long> typed = entityManager
                    .createNamedQuery("CANFENOTFIS.NamedQueryCountFindAllLike", Long.class)
                    .setParameter("CLI_NOME", filtro_CLI_NOME);
            long regCount = typed.getResultList().size();
            return regCount;
        } finally {
            entityManager.close();
        }
    }

}
