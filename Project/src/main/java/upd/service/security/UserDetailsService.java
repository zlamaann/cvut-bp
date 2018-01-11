package upd.service.security;

import upd.model.Person;
import upd.persistence.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Person person = personDao.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        return new upd.security.model.UserDetails(person);
    }
}
