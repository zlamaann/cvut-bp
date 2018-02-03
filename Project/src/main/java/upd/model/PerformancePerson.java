package upd.model;

import sun.misc.Perf;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PERFORMANCE_PERSON")
public class PerformancePerson {

    @EmbeddedId
    private PerformancePersonId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("performanceId")
    private Performance performance;

    @Column(name = "ROLE_NAME")
    private String roleName;

    public PerformancePerson() {
    }

    public PerformancePerson(Person person, Performance performance) {
        this.person = person;
        this.performance = performance;
        this.id = new PerformancePersonId(person.getId(), performance.getId());
    }

    public PerformancePerson(Person person, Performance performance, String roleName) {
        this.person = person;
        this.performance = performance;
        this.id = new PerformancePersonId(person.getId(), performance.getId());
        this.roleName = roleName;
    }

    public PerformancePersonId getId() {
        return id;
    }

    public void setId(PerformancePersonId id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformancePerson that = (PerformancePerson) o;
        return Objects.equals(person, that.person) &&
                Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(person, performance);
    }
}
