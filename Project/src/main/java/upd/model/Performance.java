package upd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private String isRegular;

    @Column(nullable = false, name = "DESCRIPTION")
    private String description;

    @OneToMany(
            mappedBy = "performane",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PerformancePerson> roles = new ArrayList<>();

    public Performance(String name, int length, String isRegular, String description) {
        this.name = name;
        this.length = length;
        this.isRegular = isRegular;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getIsRegular() {
        return isRegular;
    }

    public void setIsRegular(String isRegular) {
        this.isRegular = isRegular;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
