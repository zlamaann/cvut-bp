package upd.rest;

import java.security.Principal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import upd.exception.NotFoundException;
import upd.model.Person;
import upd.rest.util.RestUtils;
import upd.service.PersonService;

@RestController
@RequestMapping("/user")

public class PersonController extends BaseController {
    
    @Autowired
    private PersonService personService;

    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Person> getAll() {
        return personService.findAll();
    }

    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getByUsername(@PathVariable("email") String username) {
        final Person u = personService.getByEmail(username);
        if (u == null) {
            throw NotFoundException.create("User", username);
        }
        u.erasePassword();
        return u;
    }

    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getCurrent(Principal principal) {
        final String username = principal.getName();
        return getByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Person user) {
        personService.persist(user);
        if (LOG.isTraceEnabled()) {
            LOG.trace("User {} successfully registered.", user);
        }
        final HttpHeaders headers = RestUtils
                .createLocationHeaderFromCurrentUri("/{email}", user.getEmail());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
