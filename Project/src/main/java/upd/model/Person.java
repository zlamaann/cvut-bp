package upd.model;

import org.eclipse.persistence.annotations.*;
import org.eclipse.persistence.annotations.Convert;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.persistence.CollectionTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
@NamedQueries(
        {@NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email=:email")})
@ObjectTypeConverter(
        name = "roleEnumFromStringConversion",
        objectType = PersonType.class,
        dataType = String.class,
        conversionValues = {
                @ConversionValue(objectValue = "ADMIN", dataValue = "ADMIN"),
                @ConversionValue(objectValue = "OFFICE", dataValue = "OFFICE"),
                @ConversionValue(objectValue = "ACTOR", dataValue = "ACTOR"),
                @ConversionValue(objectValue = "MUSICIAN", dataValue = "MUSICIAN"),
                @ConversionValue(objectValue = "USHERETTE", dataValue = "USHERETTE"),
                @ConversionValue(objectValue = "COSTUMIER", dataValue = "COSTUMIER"),
                @ConversionValue(objectValue = "TECHNICIAN", dataValue = "TECHNICIAN"),
                @ConversionValue(objectValue = "LIGHTING", dataValue = "LIGHTING"),
                @ConversionValue(objectValue = "SOUND", dataValue = "SOUND")
        }
)
public class Person implements Serializable {

    public Person() {
    }

    public Person(String name, String surname, String password, String email, Integer phoneNumber) {
            this.name = name;
            this.surname = surname;
            this.password = password;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_PERSON")
    private Integer id;

    private List<PersonType> roles;

    @ElementCollection(targetClass = PersonType.class)
    @CollectionTable(
            name="PERSON_TYPE",
            joinColumns=@JoinColumn(name="NAME")
    )
    @Convert("roleEnumFromStringConversion")
    public List<PersonType> getRoles() {
        return roles;
    }

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "SURNAME")
    private String surname;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(nullable = false, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @ManyToOne
    @JoinColumn(name="ID_ADDRESS")
    private Address address;

    @OneToMany
    @JoinTable(name="MESSAGE_PERSON",
    joinColumns = @JoinColumn(name = "ID_PERSON")
    )
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="SHIFT_PERSON",
                    joinColumns=@JoinColumn(name="ID_PERSON"),
                    inverseJoinColumns=@JoinColumn(name="ID_SHIFT"))
    private List<Shift> shifts = new ArrayList<>();

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PerformancePerson> performances = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(List<PersonType> role) {
        this.roles = role;
    }

    public void addRole(PersonType role) {
        this.roles.add(role);
    }

    public void deleteRole(PersonType role) {
        this.roles.remove(role);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void encodePassword(PasswordEncoder encoder) {
        if (password == null || password.isEmpty()) {
            throw new IllegalStateException("This field can't be empty.");
        }
        this.password = encoder.encode(password);
    }

    public void erasePassword() {
        this.password = null;
    }

    public boolean nameEquals(Person other) {
        return other != null && name.equals(other.name) &&  surname.equals(other.surname) ;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Address getAddress() {
        return address;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMesage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public void removeShift(Shift shift) {
        shifts.remove(shift);
    }

    public List<PerformancePerson> getPerformances() {
        return performances;
    }

    public void addPerformanceRole(PerformancePerson role) {
        performances.add(role);
    }

    public void removePerformanceRole(PerformancePerson role) {
        performances.remove(role);
    }
}

