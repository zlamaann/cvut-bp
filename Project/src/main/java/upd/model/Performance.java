package upd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "PERFORMANCE")
public class Performance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_PERFORMANCE")
    private Integer id;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "LENGTH")
    private int length;

    @Column(nullable = false, name = "IS_REGULAR")
    private Boolean isRegular;

    @Column(nullable = false, name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "performance")
    @JsonIgnore
    //@JsonManagedReference
    private List<PerformancePerson> roles;

    public Performance() {
    }

    public Performance(String name, int length, Boolean isRegular, String description) {
        this.name = name;
        this.length = length;
        this.isRegular = isRegular;
        this.description = description;
    }

    public void addNewRole(Person person, String name) {
        PerformancePerson performancePerson = new PerformancePerson(person, this, name);
        roles.add(performancePerson);
        person.getPerformances().add(performancePerson);
    }

    public void removeRole(String role) {
        for (Iterator<PerformancePerson> iterator = roles.iterator();iterator.hasNext();) {
            PerformancePerson performancePerson = iterator.next();

            if (performancePerson.getPerformance().equals(this) && performancePerson.getRoleName().equals(role)) {
                iterator.remove();
                performancePerson.getPerson().getPerformances().remove(performancePerson);
                performancePerson.setPerformance(null);
                performancePerson.setPerson(null);
            }
        }

    }

    public void removePersonFromRole(Person person) {
        for (Iterator<PerformancePerson> iterator = roles.iterator();iterator.hasNext();) {
            PerformancePerson performancePerson = iterator.next();

            if (performancePerson.getPerformance().equals(this) && performancePerson.getPerson().equals(person)) {
                iterator.remove();
                performancePerson.getPerson().getPerformances().remove(performancePerson);
                performancePerson.setPerformance(null);
                performancePerson.setPerson(null);
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Boolean getIsRegular() {
        return isRegular;
    }

    public void setIsRegular(Boolean isRegular) {
        this.isRegular = isRegular;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PerformancePerson> getRoles() {
        return roles;
    }

    public void setRoles(List<PerformancePerson> roles) {
        this.roles = roles;
    }


}
