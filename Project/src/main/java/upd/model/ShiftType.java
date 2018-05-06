package upd.model;

import javax.persistence.*;

@Entity
@Table(name = "SHIFT_TYPE")
public class ShiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_SHIFT_TYPE")
    private Integer id;

    @Column(name = "NAME", insertable = false, updatable = false)
    private String name;

    @Column(name = "VALUE", insertable = false, updatable = false)
    private String value;

    public ShiftType() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

