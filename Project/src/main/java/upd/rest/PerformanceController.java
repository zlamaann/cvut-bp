package upd.rest;

import org.eclipse.persistence.sessions.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upd.model.Performance;
import upd.model.Person;
import upd.rest.util.RestUtils;
import upd.service.PerformanceService;
import upd.service.PersonService;
import upd.service.wrapper.PerformanceContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@RestController
@RequestMapping("/performance")
public class PerformanceController extends BaseController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Performance> getAll() {
        return performanceService.findAll();
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
