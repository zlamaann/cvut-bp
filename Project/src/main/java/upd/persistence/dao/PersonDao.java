package upd.persistence.dao;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import sun.misc.Perf;
import upd.model.Message;
import upd.model.Performance;
import upd.model.Person;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upd.model.Shift;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDao extends BaseDao<Person> {

    private HashMap<String,ArrayList<Person>> orderedRoles = new HashMap<>();

    public PersonDao() {
        super(Person.class);
    }

    @Transactional(readOnly = true)
    public Person findByEmail(String email) {
        if (email.equals("")) {
            return null;
        }
        try {
            return em.createNamedQuery("Person.findByEmail", Person.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Person findById(Integer id) {
        if (id == null) {
            return null;
        }
        try {
            return em.createNamedQuery("Person.findById", Person.class).setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Person> findByName(String name) {

        if (name.equals("")) {
            return null;
        }
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
            Root<Person> root = query.from(Person.class);
            Predicate predicate1 = criteriaBuilder.like(
                    criteriaBuilder.function(
                            "unaccent", String.class, criteriaBuilder.lower(root.get("name"))
                    ),
                    "%" + removeAccents(name) + "%"
            );
            Predicate predicate2 = criteriaBuilder.like(
                    criteriaBuilder.function(
                            "unaccent", String.class, criteriaBuilder.lower(root.get("surname"))
                    ),
                    "%" + removeAccents(name) + "%"
            );
            query.select(root).where(criteriaBuilder.or(predicate1,predicate2));
            TypedQuery<Person> finalQuery = em.createQuery(query);
            return finalQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional (readOnly = true)
    public List<String> getRolesByPersonId(Integer idPerson) {
        if (idPerson == null) {
            return null;
        }
        try {
            return em.createQuery("SELECT perfper.roleName FROM Person per JOIN per.performances perfper WHERE per.id = :idPerson", String.class).setParameter("idPerson", idPerson)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional (readOnly = true)
    public List<Person> getPersonByRole(String role) {
        if (role.equals("")) {
            return null;
        }
        try {
            return em.createQuery("SELECT per FROM Person per JOIN per.performances perfper WHERE perfper.roleName = :role", Person.class).setParameter("role", role)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional (readOnly = true)
    public HashMap<String,ArrayList<Person>> getRolesByPerformanceId(Integer id) {
        orderedRoles.clear();
        if (id == null) {
            return null;
        }
        try {
            Query query = em.createQuery("SELECT perfper.roleName, per FROM Person per JOIN per.performances perfper JOIN perfper.performance perf WHERE perf.id = :id", Person.class).setParameter("id", id);
            List<Object[]> unorderedRoles = query.getResultList();
            for (Object[] role : unorderedRoles) {
                String roleName = (String) role[0];
                ArrayList<Person> personList = orderedRoles.get(roleName);
                if (personList == null) {
                    personList = new ArrayList<>();
                }
                orderedRoles.put(roleName,personList);
                personList.add((Person) role[1]);
            }
            return orderedRoles;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Message> getMessages() {
        try {
            return em.createQuery("SELECT m, p.name, p.surname FROM Person p JOIN p.messages m ", Message.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static String removeAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return s;
    }

}
