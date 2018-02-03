package upd.model;

import javax.persistence.*;
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
    private String streetNumber;

    @ManyToOne
    @JoinColumn(name="ID_CITY")
    private City city ;

    public Address() {
    }

    public Address(String streetName, String streetNumber, City city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
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

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
