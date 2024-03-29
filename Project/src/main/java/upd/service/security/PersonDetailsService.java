package upd.service.security;

import upd.model.Person;
import upd.persistence.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upd.security.model.PersonDetails;

@Service
public class PersonDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Person person = personDao.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("User with username " + email + " not found.");
        }
        return new PersonDetails(person);
    }
}
