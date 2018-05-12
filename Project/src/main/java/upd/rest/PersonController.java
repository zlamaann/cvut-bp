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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upd.exception.NotFoundException;
import upd.model.Address;
import upd.model.Message;
import upd.model.Person;
import upd.model.PersonType;
import upd.rest.exception.DataConflictException;
import upd.rest.util.RestUtils;
import upd.service.PersonService;
import upd.service.PersonTypeService;

@RestController

@RequestMapping("/persons")
public class PersonController extends BaseController {
    
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonTypeService personTypeService;

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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {
        personService.persist(person);
        if (LOG.isTraceEnabled()) {
            LOG.trace("User {} successfully registered.", person);
        }
        final HttpHeaders headers = RestUtils
                .createLocationHeaderFromCurrentUri("/{email}", person);
        return person;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@PathVariable("id") Integer personId, @RequestBody Person person) {
        if (!personId.equals(person.getId())) {
            throw new DataConflictException(
                    "Person id " + personId + " in the URL does not match the person id " + person.getId() +
                            " in the data.");
        }
        if (!personService.exists(personId)) {
            throw NotFoundException.create("Person", personId);
        }
        personService.update(person);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Person {} updated.", person);
        }
        person.erasePassword();
        return person;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Person delete(@PathVariable("id") Integer personId) {
        final Person person = personService.find(personId);
        if (person == null) {
            throw NotFoundException.create("Person", personId);
        }
        personService.remove(person);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Person {} successfully removed.", person);
        }
        return person;
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
        if (principal == null) return null;
        final String username = principal.getName();
        Person person = getByEmail(username);
        return person;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getMessages() {
        List<Message> messages = personService.getMessages();
        return messages;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPersonTypes(@RequestBody List<Integer> types, @PathVariable Integer id) {
        Person person = personService.find(id);
        PersonType personType;
        for (Integer typeId : types) {
            personType = personTypeService.find(typeId);
            person.addRole(personType);
        }
        personService.merge(person);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Types to user {} successfully added.", person);
        }
        final HttpHeaders headers = RestUtils
                .createLocationHeaderFromCurrentUri("/{id}", id);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonType> getAllTypes() {
        return personTypeService.findAll();
    }

}
