package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upd.model.Shift;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.ShiftDao;

import java.util.List;

@Service
public class ShiftService extends BaseService<Shift> {

    @Autowired
    private ShiftDao shiftDao;

    @Override
    protected GenericDao<Shift> getPrimaryDao() {
        return shiftDao;
    }

    @Transactional(readOnly = true)
    public List<Shift> getTodayShifts() {
        return shiftDao.findByToday();
    }

    @Transactional(readOnly = true)
    public List<Shift> getWeekShifts() {
        return shiftDao.findByWeek();
    }

    @Transactional(readOnly = true)
    public List<Shift> getMonthShifts() {
        return shiftDao.findByMonth();
    }

    @Transactional(readOnly = true)
    public List<Shift> getShiftByPersonId(Integer id) {
        return shiftDao.getShiftsByPersonId(id);
    }
}
