package upd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LOCATION")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_LOCATION")
    private Integer id;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "DESCRIPTION")
    private String description;

    public Location() {
    }

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
