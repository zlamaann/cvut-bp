package upd.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PerformancePersonId implements Serializable {

    @Column(name = "ID_PERSON")
    private Integer personId;

    @Column(name = "ID_PERFORMANCE")
    private Integer performanceId;

    public PerformancePersonId(Integer personId, Integer performanceId) {
        this.personId = personId;
        this.performanceId = performanceId;
    }

    public PerformancePersonId() {
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Integer performanceId) {
        this.performanceId = performanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformancePersonId that = (PerformancePersonId) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(performanceId, that.performanceId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(personId, performanceId);
    }
}
