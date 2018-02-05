package upd.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import upd.exception.NotFoundException;
import upd.model.Performance;
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
    public List<Person> getAll() {
        return personService.findAll();
    }

    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/login/{email}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getByEmail(@PathVariable("email") String email) {
        final Person u = personService.getByEmail(email);
        if (u == null) {
            throw NotFoundException.create("User", email);
        }
        u.erasePassword();
        return u;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getByName(@PathVariable("name") String name) {
        final List<Person> u = personService.getByName(name);
        if (u == null) {
            throw NotFoundException.create("User", name);
        }
        return u;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getRolesByPersonId(@PathVariable("id") Integer id) {
        final List<String> u = personService.getRolesByPersonId(id);
        if (u == null) {
            throw NotFoundException.create("User with id ", id);
        }
        return u;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/role/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersonByRole(@PathVariable("role") String role) {
        final List<Person> u = personService.getPersonByRole(role);
        if (u == null) {
            throw NotFoundException.create("Role ", role);
        }
        return u;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roles/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,ArrayList<Person>> getAllRolesByPerformanceId (@PathVariable("id") Integer id) {
        final HashMap<String,ArrayList<Person>> u = personService.getRolesByPerformanceId(id);
        if (u == null) {
            throw NotFoundException.create("Performance with ", id);
        }
        return u;
    }

    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getCurrent(Principal principal) {
        final String username = principal.getName();
        return getByEmail(username);
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
