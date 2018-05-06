package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upd.model.Person;
import upd.model.Shift;

import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository
public class ShiftDao extends BaseDao<Shift> {

    Calendar calendar = Calendar.getInstance();
    Date today = new Date();

    public ShiftDao() {
        super(Shift.class);
    }

    @Transactional(readOnly = true)
    public List<Shift> findAllSorted() {
        try {
            return em.createQuery("SELECT s FROM Shift s ORDER BY s.timeFrom", Shift.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Transactional(readOnly = true)
    public List<Shift> findByToday() {

        calendar.setTime(today);
        calendar.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,0,0);
        Date from = calendar.getTime();
        calendar.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,23,59);
        Date to = calendar.getTime();

        try {
            return em.createNamedQuery("Shift.findByDate", Shift.class)
                    .setParameter("from", from, TemporalType.DATE)
                    .setParameter("to", to, TemporalType.DATE)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Shift> findByWeek() {

        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.FRANCE).getFirstDayOfWeek();
        DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
        //calendar.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,0,0);
        Date from = Date.from(LocalDate.now().with(TemporalAdjusters.previousOrSame(firstDayOfWeek)).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(LocalDate.now().with(TemporalAdjusters.nextOrSame(lastDayOfWeek)).atStartOfDay(ZoneId.systemDefault()).toInstant());

        try {
            return em.createNamedQuery("Shift.findByDate", Shift.class)
                    .setParameter("from", from, TemporalType.DATE)
                    .setParameter("to", to, TemporalType.DATE)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Shift> findByMonth() {

        calendar.set(Calendar.YEAR,Calendar.MONTH,0,0,0);
        Date from = calendar.getTime();
        calendar.set(Calendar.YEAR,Calendar.MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH),23,59);
        Date to = calendar.getTime();

        try {
            return em.createNamedQuery("Shift.findByDate", Shift.class)
                    .setParameter("from", from, TemporalType.DATE)
                    .setParameter("to", to, TemporalType.DATE)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public List<Shift> getShiftsByPersonId(Integer id) {
        try {
            return em.createNamedQuery("Person.findShiftByPersonId", Shift.class).setParameter("id", id)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
