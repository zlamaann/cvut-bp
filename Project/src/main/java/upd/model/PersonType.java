package upd.model;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_TYPE")
public class PersonType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_PERSON_TYPE")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    public PersonType() {
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
