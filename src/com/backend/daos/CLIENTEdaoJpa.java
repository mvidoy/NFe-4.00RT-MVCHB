package com.backend.daos;

import com.backend.dtos.CLIENTEdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.CLIENTE;
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
import java.lang.annotation.AnnotationFormatError;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class CLIENTEdaoJpa implements Serializable {

    public CLIENTEdaoJpa(EntityManagerFactory emf) {
        this.emf = (EntityManagerFactory) new ConnectionFactory().getConnection();
    }
    private EntityManagerFactory emf = null;

    public CLIENTEdaoJpa() {

    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(CLIENTE eCLIENTE) {
        boolean validated = true;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.persist(eCLIENTE);
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

    public void update(CLIENTE eCLIENTE) throws NonexistentEntityException, Exception {

        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            em.merge(eCLIENTE);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = Long.parseLong(eCLIENTE.getCLI_CODI());
                if (findCLIENTE(id) == null) {
                    throw new NonexistentEntityException("The eCLIENTE with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(String ID_CLIENTE) throws NonexistentEntityException {
        boolean validated = false;
        EntityManager em = null;
        try {
            em = new ConnectionFactory().getConnection();
            em.getTransaction().begin();
            CLIENTE eCLIENTE;
            try {
                eCLIENTE = findCodigo(ID_CLIENTE);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eCLIENTE with id no longer exists.", enfe);
            }
            em.remove(em.merge(eCLIENTE));
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

    public List<CLIENTEdto> findCLIENTEEntities() throws ParseException, SQLException {
        return findCLIENTEEntities("", "", true, -1, -1);
    }

    public List<CLIENTEdto> findCLIENTEEntities(String filtro_cli_tiposgq, String filtro_cli_nome) throws ParseException, SQLException {
        return findCLIENTEEntities(filtro_cli_tiposgq, filtro_cli_nome, true, -1, -1);
    }

    public List<CLIENTEdto> findCLIENTEEntities(
            String filtro_cli_tiposgq,
            String filtro_cli_nome,
            int maxResults,
            int firstResult) throws ParseException, SQLException {
        return findCLIENTEEntities(
                filtro_cli_tiposgq,
                filtro_cli_nome,
                false,
                maxResults,
                firstResult);
    }

    private List<CLIENTEdto> findCLIENTEEntities(
            String filtro_cli_tiposgq,
            String filtro_cli_nome,
            boolean all,
            int maxResults,
            int firstResult) throws ParseException, SQLException {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CLIENTE> cq = cb.createQuery(CLIENTE.class);
            Root<CLIENTE> cliente = cq.from(CLIENTE.class);
            Predicate tipoPredicate = cb.equal(cliente.get("CLI_TIPOSGQ"), filtro_cli_tiposgq.trim().toUpperCase());
            Predicate nomePredicate = cb.like(cliente.get("CLI_NOME"), "%" + filtro_cli_nome.trim().toUpperCase() + "%");
            Predicate notNullNome = cb.isNotNull(cliente.get("CLI_NOME"));
            //Predicate notNullISENTOICMS = cb.isNotNull(cliente.get("CLI_ISENTOICMS"));
            //cq.where(notNullISENTOICMS);
            cq.where(notNullNome);
            if (filtro_cli_tiposgq.trim().length() > 0) {
                if (!filtro_cli_tiposgq.trim().equals("TODOS")) {
                    cq.where(notNullNome, tipoPredicate);
                }
                if (filtro_cli_nome.trim().length() > 0) {
                    if (!filtro_cli_tiposgq.trim().equals("TODOS")) {
                        cq.where(notNullNome, tipoPredicate, nomePredicate);
                    } else {
                        cq.where(notNullNome, nomePredicate);
                    }
                }
            }

            cq.orderBy(cb.asc(cliente.get("CLI_NOME")),
                    cb.asc(cliente.get("CLI_CODI")));

            TypedQuery<CLIENTE> q = em.createQuery(cq);

            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }

            List<CLIENTEdto> ListCLIENTEdto = new ArrayList<CLIENTEdto>();
            List<CLIENTE> lst = q.getResultList();

            for (CLIENTE cli : lst) {
                CLIENTEdto cLIENTEdto = CLIENTEdto.convertCLIENTEtoCLIENTEdto(cli, false);
                ListCLIENTEdto.add(cLIENTEdto);
            }

            return ListCLIENTEdto;
        } finally {
            em.close();
        }
    }

    public CLIENTE findCLIENTE(Long id) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            return em.find(CLIENTE.class, id);
        } finally {
            em.close();
        }
    }

    public int getCLIENTECount() {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CLIENTE> rt = cq.from(CLIENTE.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static CLIENTE findCodigo(@NotNull final String empcodi) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CLIENTE> typed = entityManager
                    .createNamedQuery("CLIENTE.findByCliCodi", CLIENTE.class)
                    .setParameter("CLI_CODI", empcodi);
            return typed.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static CLIENTE findLastCliCodi() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CLIENTE> typed = entityManager
                    .createNamedQuery("CLIENTE.findLastCliCodi", CLIENTE.class);
            return typed.getResultList().get(0);
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static CLIENTE findCnpj(@NotNull final String empcnpj) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            TypedQuery<CLIENTE> typed = entityManager
                    .createNamedQuery("CLIENTE.findByCliCgc0", CLIENTE.class)
                    .setParameter("CLI_CGC0", empcnpj);
            return typed.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public static boolean validEntity(CLIENTEdto classe) {
        boolean validated = false;
        try {
            Configuration<?> configuration = Validation.byDefaultProvider().configure();
            configuration.messageInterpolator(new ParameterMessageInterpolator());
            javax.validation.Validator validator = configuration.buildValidatorFactory().getValidator();
            Set<ConstraintViolation<CLIENTEdto>> constraintViolations
                    = validator.validate(classe);
            String msgError = "";
            System.setProperty("MsgInvalid", msgError);
            for (ConstraintViolation error : constraintViolations) {
                msgError += error.getMessage() + "\n";
            }
            System.setProperty("MsgInvalid", msgError);
            if (msgError.length() > 0) {
                validated = true;
            }
            return validated;
        } catch (AnnotationFormatError ex) {
            System.setProperty("MsgInvalid", ex.toString());
            validated = false;
            return validated;
        } finally {
            return validated;
        }
    }
}
