package upd.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SHIFT")
@DiscriminatorValue("SHIFT")
@NamedQueries(
        {
                @NamedQuery(name = "Shift.findByDate", query = "SELECT s, p FROM Shift s JOIN Performance p ON s.performance = p WHERE s.timeFrom BETWEEN :from AND :to"),
                @NamedQuery(name = "Person.findShiftByPersonId", query = "SELECT s FROM Person p JOIN p.shifts s WHERE p.id=:id")

        })
public class Shift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_SHIFT")
    private Integer id;

    @Column(nullable = false, name = "TIME_FROM")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date timeFrom;

    @Column(nullable = false, name = "TIME_TO")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date timeTo;

    @Column(nullable = false, name = "NOTES")
    private String notes;

    @ManyToOne
    @JoinColumn(name="ID_LOCATION")
    private Location location ;

    @ManyToOne
    @JoinColumn(name="ID_PERFORMANCE")
    private Performance performance ;

    @ManyToOne
    @JoinColumn(name="ID_SHIFT_TYPE")
    private ShiftType type;

    public Shift() {
    }

    public Shift(Date timeFrom, Date timeTo, String notes, ShiftType type) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.notes = notes;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public ShiftType getType() {
        return type;
    }

    public void setType(ShiftType type) {
        this.type = type;
    }
}
