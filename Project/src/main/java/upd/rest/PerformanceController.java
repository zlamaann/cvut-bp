package upd.rest;

import org.eclipse.persistence.sessions.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upd.exception.NotFoundException;
import upd.model.Performance;
import upd.model.Person;
import upd.rest.exception.DataConflictException;
import upd.rest.util.RestUtils;
import upd.service.PerformanceService;
import upd.service.PersonService;
import upd.service.wrapper.PerformanceContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@RestController
@RequestMapping("/performances")
public class PerformanceController extends BaseController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Performance> getAll() {
        List<Performance> performances = performanceService.findAll();
        return performances;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Performance performance) {
        performanceService.persist(performance);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Performance {} successfully created.", performance);
        }
        final HttpHeaders headers = RestUtils
                .createLocationHeaderFromCurrentUri("/{name}", performance.getName());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer performanceId, @RequestBody Performance performance) {
        if (!performanceId.equals(performance.getId())) {
            throw new DataConflictException(
                    "Performance id " + performanceId + " in the URL does not match the performance id " + performance.getId() +
                            " in the data.");
        }
        if (!performanceService.exists(performanceId)) {
            throw NotFoundException.create("Performance", performanceId);
        }
        performanceService.update(performance);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performance {} updated.", performance);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer performanceId) {
        final Performance performance = performanceService.find(performanceId);
        if (performance == null) {
            throw NotFoundException.create("Person", performanceId);
        }
        performanceService.remove(performance);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performance {} successfully removed.", performance);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{idPerf}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody HashMap<String,ArrayList<Integer>> hashMap, @PathVariable Integer idPerf) {
        Performance performance = performanceService.find(idPerf);
        String roleName;
        Person person;
        ArrayList<Integer> personList;
        for (Map.Entry<String, ArrayList<Integer>> entry : hashMap.entrySet()) {
            roleName = entry.getKey();
            personList = entry.getValue();
            for (Integer id : personList) {
                person = personService.find(id);
                performance.addNewRole(person,roleName);
            }
        }

        performanceService.merge(performance);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Roles to performance {} successfully added.", performance);
        }
        final HttpHeaders headers = RestUtils
                .createLocationHeaderFromCurrentUri("/{name}", performance.getName());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
