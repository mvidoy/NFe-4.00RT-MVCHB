package com.backend.daos;

import com.backend.dtos.CANFEDUPLICdto;
import static com.backend.dtos.CANFEDUPLICdto.convertCANFEDUPLICdtotoCANFEDUPLIC;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.CANFEDUPLIC;
import com.backend.services.ConnectionFactory;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import org.hibernate.Hibernate;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class CANFEDUPLICdaoJpa implements Serializable {

    public static int CANFEDUPLICRecordCount; //novo
    private final List<Selection<?>> projection = new ArrayList<>();

    public CANFEDUPLICdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int getCANFEDUPLICRecordCount() {//novo
        return CANFEDUPLICRecordCount;
    }

    public void setCANFEDUPLICRecordCount(int CANFEDUPLICRecordCount) {//novo
        this.CANFEDUPLICRecordCount = CANFEDUPLICRecordCount;
    }

    public CANFEDUPLICdaoJpa() {

    }

    public boolean create(CANFEDUPLIC caNFEDUPLIC) {
        boolean validated = true;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(caNFEDUPLIC);
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

    public void update(CANFEDUPLIC caNFEDUPLIC) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            validateFieldLengths(caNFEDUPLIC.getCANFEIRDUPL());
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(caNFEDUPLIC);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            ex.printStackTrace(); // Mostra o stacktrace completo no console
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void validateFieldLengths(Object entity) throws Exception {
        // Percorre todos os campos da classe
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Permite acesso a campos privados
            Object value = field.get(entity); // Obtém o valor do campo

            // Verifica se o campo é do tipo String
            if (value instanceof String) {
                String stringValue = (String) value;

                // Verifica se o comprimento da string é maior que 10
                if (stringValue.length() > 10) {
                    System.out.println("Erro: Campo '" + field.getName() + "' excede o limite de 10 caracteres.");
                    System.out.println("Valor: " + stringValue);
                }
            }
        }
    }

    public static boolean delete(String PAR_CODI) throws NonexistentEntityException, ParseException, SQLException {
        boolean validated = false;
        EntityManager em = null;
        em = new ConnectionFactory().getConnection();
        List<CANFEDUPLICdto> caNFEDUPLICdto = NamedQueryFindAllNota(PAR_CODI);
        for (int i = 0; i < caNFEDUPLICdto.size(); i++) {
            CANFEDUPLIC caNFEDUPLIC = new CANFEDUPLIC();
            caNFEDUPLIC = convertCANFEDUPLICdtotoCANFEDUPLIC(caNFEDUPLICdto.get(i));
            em.getTransaction().begin();
            em.remove(em.merge(caNFEDUPLIC));
            em.getTransaction().commit();
        }
        validated = true;
        if (em != null) {
            em.close();
        }
        return validated;
    }

    public List<CANFEDUPLICdto> findCANFEDUPLICEntities() {
        return findCANFEDUPLICEntities("", true, -1, -1);
    }

    public List<CANFEDUPLICdto> findCANFEDUPLICEntities(String par_codi, String par_parc) {
        return findCANFEDUPLICEntities(par_parc, true, -1, -1);
    }

    public List<CANFEDUPLICdto> findCANFEDUPLICEntities(
            String par_parc,
            int maxResults,
            int firstResult) {
        return findCANFEDUPLICEntities(
                par_parc,
                false,
                maxResults,
                firstResult);
    }

    private List<CANFEDUPLICdto> findCANFEDUPLICEntities(
            String par_parc,
            boolean all,
            int maxResults,
            int firstResult) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CANFEDUPLICdto> cq = cb.createQuery(CANFEDUPLICdto.class);
            Root<CANFEDUPLIC> caNFEDUPLIC = cq.from(CANFEDUPLIC.class);
            Predicate par_parcPredicate = cb.equal(caNFEDUPLIC.get("PAR_CODI"), par_parc.trim());
            cq.where(par_parcPredicate);
            cq.orderBy(cb.asc(caNFEDUPLIC.get("PAR_CODI")),
                    cb.asc(caNFEDUPLIC.get("PAR_PARC")));
            TypedQuery<CANFEDUPLICdto> q = em.createQuery(cq);
            List<CANFEDUPLICdto> ListCANFEDUPLIC = q.getResultList();
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Object[]> NamedQueryListByNota(@NotNull final String par_codi) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<Object[]> typed = entityManager
                    .createNamedQuery("CANFEDUPLIC.NamedQueryListByNota", Object[].class)
                    .setParameter("PAR_CODI", par_codi);
            return typed.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public CANFEDUPLIC findCANFEDUPLIC(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(CANFEDUPLIC.class, id);
        } finally {
            em.close();
        }
    }

    public int getCANFEDUPLICCount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CANFEDUPLIC> rt = cq.from(CANFEDUPLIC.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static CANFEDUPLIC NamedQueryFindByNotaParc(@NotNull final String par_codi, final String par_parc) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEDUPLIC> typed = entityManager
                    .createNamedQuery("CANFEDUPLIC.NamedQueryFindByNotaParc", CANFEDUPLIC.class)
                    .setParameter("PAR_CODI", par_codi)
                    .setParameter("PAR_PARC", par_parc);

            CANFEDUPLIC caNFEDUPLIC = new CANFEDUPLIC();
            caNFEDUPLIC = typed.getSingleResult();
            //if (caNFEDUPLIC.getCANFEIRDUPL() != null) {
            Hibernate.initialize(caNFEDUPLIC.getCANFEIRDUPL());
            //}
            return caNFEDUPLIC;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static CANFEDUPLIC findByLastParCodi() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEDUPLIC> typed = entityManager
                    .createNamedQuery("CANFEDUPLIC.findByLastParCodi", CANFEDUPLIC.class);
            return typed.getResultList().get(0);
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<CANFEDUPLICdto> NamedQueryFindAllNota(String PAR_CODI) throws ParseException, SQLException {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEDUPLIC> typed = entityManager
                    .createNamedQuery("CANFEDUPLIC.NamedQueryFindAllNota", CANFEDUPLIC.class)
                    .setParameter("PAR_CODI", PAR_CODI);
            List<CANFEDUPLICdto> ListCANFEDUPLICdto = new ArrayList<CANFEDUPLICdto>();
            List<CANFEDUPLIC> lst = typed.getResultList();

            for (CANFEDUPLIC caNFEDUPLIC : lst) {
                CANFEDUPLICdto caNFEDUPLICdto = CANFEDUPLICdto.convertCANFEDUPLICtoCANFEDUPLICdto(caNFEDUPLIC, false);
                ListCANFEDUPLICdto.add(caNFEDUPLICdto);
            }
            return ListCANFEDUPLICdto;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(CANFEDUPLICdto classe) throws Exception {
        boolean wValida = false;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
        Set<ConstraintViolation<CANFEDUPLICdto>> constraintViolations
                = validator.validate(classe);
        String msgError = "";
        System.setProperty("MsgInvalid", msgError);
        for (ConstraintViolation error : constraintViolations) {
            msgError += error.getMessage() + "\n";
        }
        System.setProperty("MsgInvalid", msgError);
        // Se houver erros, loga no console e seta wValida como true
        if (msgError.length() > 0) {
            System.out.println("Erros de validação: \n" + msgError.toString());
            wValida = true;
        }
        return wValida;
    }

    public static List<CANFEDUPLICdto> NamedQueryFindAll(
            String filtro_CLI_NOME,
            String filtro_PAR_CONTA,
            String filtro_PAR_STATUS,
            String filtro_PAR_CODI_inical,
            String filtro_PAR_CODI_final,
            int pageSize,
            int pageNumber) throws ParseException, SQLException {

        String bANCO = "";
        String aGENCIA = "";
        String cONTA = "";
        if (!filtro_PAR_CONTA.trim().equals("N/A")) {
            int primeiroSeparador = filtro_PAR_CONTA.indexOf(":");
            int segundoSeparador = filtro_PAR_CONTA.indexOf("/");
            if (primeiroSeparador != -1 && segundoSeparador != -1) {
                bANCO = filtro_PAR_CONTA.substring(0, primeiroSeparador);
                aGENCIA = filtro_PAR_CONTA.substring(primeiroSeparador + 1, segundoSeparador);
                cONTA = filtro_PAR_CONTA.substring(segundoSeparador + 1);
            }
        }

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<Object[]> typed = entityManager
                    .createNamedQuery("CANFEDUPLIC.NamedQueryFindAllLike", Object[].class)
                    .setParameter("CLI_NOME", filtro_CLI_NOME)
                    .setParameter("PAR_STATUS", filtro_PAR_STATUS)
                    .setParameter("PAR_CODIINICIAL", filtro_PAR_CODI_inical)
                    .setParameter("PAR_CODIFINAL", filtro_PAR_CODI_final)
                    .setParameter("PAR_BANCO", bANCO)
                    .setParameter("PAR_AGENCIA", aGENCIA)
                    .setParameter("PAR_CONTA", cONTA);

            
            typed.setMaxResults(1000);
            CANFEDUPLICdaoJpa.CANFEDUPLICRecordCount = typed.getResultList().size(); //novo

            //Pagination
            int FirstResult = pageSize * pageNumber;
            if (FirstResult > CANFEDUPLICdaoJpa.CANFEDUPLICRecordCount) {//novo
                FirstResult = CANFEDUPLICdaoJpa.CANFEDUPLICRecordCount;//novo
            }
            //typed.setFirstResult(FirstResult);
            //typed.setMaxResults(pageSize);

            List<CANFEDUPLICdto> ListCANFEDUPLICdto = new ArrayList<CANFEDUPLICdto>();
            List<Object[]> lst = typed.getResultList();

            for (Object o[] : lst) {
                CANFEDUPLICdto caNFEDUPLICdto = CANFEDUPLICdto.convertCANFEDUPLICtoCANFEDUPLICdto((CANFEDUPLIC) o[0], false);
                if (caNFEDUPLICdto != null) {
                    if (caNFEDUPLICdto.getPAR_STATUS() != null
                            && filtro_PAR_STATUS.equals("DIGITADO")
                            && caNFEDUPLICdto.getPAR_STATUS().trim().equals("DIGITADO")
                            && caNFEDUPLICdto.getCANFEIRDUPLdto() != null
                            && caNFEDUPLICdto.getCANFEIRDUPLdto().size() > 0) {
                    } else if (caNFEDUPLICdto.getId() != null) {
                        ListCANFEDUPLICdto.add(caNFEDUPLICdto);
                    }
                }
            }
            return ListCANFEDUPLICdto;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<CANFEDUPLICdto> NamedQueryFindAll(int pageSize, int pageNumber) throws ParseException, SQLException {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEDUPLIC> typed = entityManager
                    .createNamedQuery("CANFEDUPLIC.NamedQueryFindAll", CANFEDUPLIC.class);

            int FirstResult = pageSize * pageNumber;
            if (FirstResult > typed.getMaxResults()) {
                FirstResult = typed.getMaxResults() / pageSize;
            }
            typed.setFirstResult(FirstResult);
            typed.setMaxResults(pageSize);

            List<CANFEDUPLICdto> ListCANFEDUPLICdto = new ArrayList<CANFEDUPLICdto>();
            List<CANFEDUPLIC> lst = typed.getResultList();
            for (CANFEDUPLIC caNFEDUPLIC : lst) {
                CANFEDUPLICdto caNFEDUPLICdto = CANFEDUPLICdto.convertCANFEDUPLICtoCANFEDUPLICdto(caNFEDUPLIC, false);
                ListCANFEDUPLICdto.add(caNFEDUPLICdto);
            }
            return ListCANFEDUPLICdto;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static class CANFEDUPLICtotalRegistrosSingleton {

        private static CANFEDUPLICtotalRegistrosSingleton instance;
        private int totalRegistros;

        private CANFEDUPLICtotalRegistrosSingleton() {
            // Construtor privado para evitar instanciação direta
        }

        public static CANFEDUPLICtotalRegistrosSingleton getInstance() {
            if (instance == null) {
                instance = new CANFEDUPLICtotalRegistrosSingleton();
            }
            return instance;
        }
    }

    public static long NamedQueryCountFindAllLike(String filtro_CLI_NOME) throws ParseException, SQLException {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<Long> typed = entityManager
                    .createNamedQuery("CANFEDUPLICS.NamedQueryCountFindAllLike", Long.class)
                    .setParameter("CLI_NOME", filtro_CLI_NOME);
            long regCount = typed.getResultList().size();
            return regCount;
        } finally {
            entityManager.close();
        }
    }

    public static List<CANFEDUPLICdto> NamedQueryFindByArquivoRemessa(String fileName) throws ParseException, SQLException {

        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CANFEDUPLIC> typed = entityManager
                    .createNamedQuery("CANFEDUPLIC.NamedQueryFindByArquivoRemessa", CANFEDUPLIC.class)
                    .setParameter("PAR_ARQREMESSA", fileName);
            List<CANFEDUPLICdto> ListCANFEDUPLICdto = new ArrayList<CANFEDUPLICdto>();
            List<CANFEDUPLIC> lst = typed.getResultList();

            for (CANFEDUPLIC caNFEDUPLIC : lst) {
                CANFEDUPLICdto caNFEDUPLICdto = CANFEDUPLICdto.convertCANFEDUPLICtoCANFEDUPLICdto(caNFEDUPLIC, false);
                ListCANFEDUPLICdto.add(caNFEDUPLICdto);
            }
            return ListCANFEDUPLICdto;
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

}
