package upd.persistence.dao;

import upd.model.Performance;
import upd.model.Person;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upd.model.Shift;

import java.text.Normalizer;
import java.util.List;

@Repository
public class PersonDao extends BaseDao<Person> {

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

    public static String removeAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return s;
    }

}
