package upd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SHIFT")
public class Shift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_SHIFT")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "TIME_FROM")
    private Date timeFrom;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "TIME_TO")
    private Date timeTo;

    @Column(nullable = false, name = "NOTES")
    private String notes;

    @ManyToOne
    @JoinColumn(name="ID_LOCATION")
    private Location location ;

    @ManyToOne
    @JoinColumn(name="ID_PERFORMANCE")
    private Performance performance ;

    @Enumerated(EnumType.STRING)
    private ShiftType type;

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
