package upd.model;

import sun.misc.Perf;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

@Entity
@Table(name = "PERFORMANCE_PERSON")
public class PerformancePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_PERFORMANCE_PERSON")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSON")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ID_PERFORMANCE")
    private Performance performance;

    @Column(name = "ROLE_NAME")
    private String roleName;

    public PerformancePerson() {
    }

    public PerformancePerson(Person person, Performance performance) {
        this.person = person;
        this.performance = performance;
        performance.getRoles().add(this);
        person.getPerformances().add(this);
    }

    public PerformancePerson(Person person, Performance performance, String roleName) {
        this.person = person;
        this.performance = performance;
        this.roleName = roleName;
        performance.getRoles().add(this);
        person.getPerformances().add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
