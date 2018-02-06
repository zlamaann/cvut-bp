package upd.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_ADDRESS")
    private Integer id;

    @Column(nullable = false, name = "STREET_NAME")
    private String streetName;

    @Column(nullable = false, name = "STREET_NUMBER")
    private Integer streetNumber;

    @Column(nullable = false, name = "CITY")
    private String city;

    @Column(nullable = false, name = "POSTAL_CODE")
    private Integer postalCode;

    public Address() {
    }

    public Address(String streetName, Integer streetNumber, String city, Integer postalCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
