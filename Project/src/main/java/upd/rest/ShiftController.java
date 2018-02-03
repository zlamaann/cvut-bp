package upd.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import upd.exception.NotFoundException;
import upd.model.Person;
import upd.model.Shift;
import upd.service.PersonService;
import upd.service.ShiftService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/shift")
public class ShiftController extends BaseController {

    @Autowired
    private ShiftService shiftService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Shift> getAll() {
        return shiftService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shift> getShiftByPersonId(@PathVariable("id") Integer id) {
        final List<Shift> shifts = shiftService.getShiftByPersonId(id);

        return shifts;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shift> getTodayShifts() {
        final List<Shift> shifts = shiftService.getTodayShifts();
        return shifts;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shift> getWeekShifts() {
        final List<Shift> shifts = shiftService.getWeekShifts();
        return shifts;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shift> getMonthShifts() {
        final List<Shift> shifts = shiftService.getMonthShifts();
        return shifts;
    }
}