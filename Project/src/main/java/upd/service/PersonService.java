package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upd.exception.UsernameExistsException;
import upd.exception.ValidationException;
import upd.model.Message;
import upd.model.Performance;
import upd.model.Person;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.PersonDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public Person getById(Integer id) {
        return personDao.findById(id);
    }

    public List<Person> getByName(String name) {
        return personDao.findByName(name);
    }

    public List<String> getRolesByPersonId(Integer id) {
        return personDao.getRolesByPersonId(id);
    }

    public List<Person> getPersonByRole(String roleName) {
        return personDao.getPersonByRole(roleName);
    }

    public List<Message> getMessages() {
        return personDao.getMessages();
    }

    public HashMap<String,ArrayList<Person>> getRolesByPerformanceId(Integer id) {
        return personDao.getRolesByPerformanceId(id);
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
