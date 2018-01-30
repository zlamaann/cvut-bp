package upd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CITY")
@NamedQueries(
        {@NamedQuery(name = "City.findByPostalCode", query = "SELECT u FROM City u WHERE u.postalCode=:postalCode")})
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_CITY")
    private Integer id;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column( name = "POST_CODE")
    private Integer postalCode;

    public City(){
    }

    public City(String name, Integer postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getName() {
        if (name == null) return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
