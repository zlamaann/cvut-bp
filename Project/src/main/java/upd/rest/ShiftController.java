package upd.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upd.exception.NotFoundException;
import upd.model.Location;
import upd.model.Person;
import upd.model.Shift;
import upd.model.ShiftType;
import upd.rest.exception.DataConflictException;
import upd.rest.util.RestUtils;
import upd.service.LocationService;
import upd.service.PersonService;
import upd.service.ShiftService;
import upd.service.ShiftTypeService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController extends BaseController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ShiftTypeService shiftTypeService;



    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Shift> getAll() {
        return shiftService.getAllSorted();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shift> getShiftByPersonId(@PathVariable("id") Integer id) {
        final List<Shift> shifts = shiftService.getShiftByPersonId(id);

        return shifts;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/calendar/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shift> getShifts(@PathVariable String type) {
        List<Shift> shifts = null;
        switch (type) {
            case "daily":
                shifts = shiftService.getTodayShifts();
                break;
            case "week":
                shifts = shiftService.getWeekShifts();
                break;
            case "month":
                shifts = shiftService.getMonthShifts();
                break;
        }
        return shifts;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Shift shift) {
        shiftService.persist(shift);
        if (LOG.isTraceEnabled()) {
            LOG.debug("Shift {} successfully created.", shift);
        }
        final HttpHeaders headers = RestUtils
                .createLocationHeaderFromCurrentUri("/{shift}", shift);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer shiftId, @RequestBody Shift shift) {
        if (!shiftId.equals(shift.getId())) {
            throw new DataConflictException(
                    "Shift id " + shiftId + " in the URL does not match the shift id " + shift.getId() +
                            " in the data.");
        }
        if (!shiftService.exists(shiftId)) {
            throw NotFoundException.create("Shift", shiftId);
        }
        shiftService.update(shift);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Shift {} updated.", shift);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer shiftId) {
        final Shift shift = shiftService.find(shiftId);
        if (shift == null) {
            throw NotFoundException.create("Shift", shiftId);
        }
        shiftService.remove(shift);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Shift {} successfully removed.", shift);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShiftType> getAllTypes() {
        return shiftTypeService.findAll();
    }


}
