package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upd.exception.UsernameExistsException;
import upd.exception.ValidationException;
import upd.model.Person;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.PersonDao;

@Service
public class PersonService extends BaseService<Person> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonDao personDao;

    @Override
    protected GenericDao<Person> getPrimaryDao() {
        return personDao;
    }

    public Person getByEmail(String email) {
        return personDao.findByEmail(email);
    }

    public void persist(Person person) {
        if (getByEmail(person.getEmail()) != null) {
            throw new UsernameExistsException("E-mail " + person.getEmail() + " already exists.");
        }
        try {
            person.encodePassword(passwordEncoder);
        } catch (IllegalStateException e) {
            throw new ValidationException(e.getMessage());
        }
        personDao.persist(person);
    }

    @Override
    public void update(Person instance) {
        final Person orig = personDao.find(instance.getId());
        if (orig == null) {
            throw new IllegalArgumentException("Cannot update person identifier.");
        }
        if (!orig.getPassword().equals(instance.getPassword())) {
            instance.encodePassword(passwordEncoder);
        }
        personDao.update(instance);
    }

    public boolean exists(String email) {
        return personDao.findByEmail(email) != null;
    }
}