package upd.persistence.dao;

import upd.model.Person;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upd.model.Shift;

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


}
