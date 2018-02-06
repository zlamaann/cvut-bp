package upd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        {
                @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE LOWER(p.email) = LOWER(:email)"),
                @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
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

    @ManyToMany
    @JoinTable
            (name="PERSON_TYPE_PERSON",
                    joinColumns=@JoinColumn(name="ID_PERSON"),
                    inverseJoinColumns=@JoinColumn(name="ID_PERSON_TYPE"))
    private List<PersonType> roles;


    public List<PersonType> getRoles() {
        return roles;
    }

    @Column(nullable = false, name = "FIRST_NAME")
    private String name;

    @Column(nullable = false, name = "LAST_NAME")
    private String surname;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(nullable = false, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="ID_ADDRESS")
    private Address address;

    @OneToMany
    @JoinTable
            (name="MESSAGE_PERSON",
                    joinColumns=@JoinColumn(name="ID_PERSON"),
                    inverseJoinColumns=@JoinColumn(name="ID_MESSAGE"))
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="SHIFT_PERSON",
                    joinColumns=@JoinColumn(name="ID_PERSON"),
                    inverseJoinColumns=@JoinColumn(name="ID_SHIFT"))
    private List<Shift> shifts = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<PerformancePerson> performances;

    public List<PerformancePerson> getPerformances() {
        return performances;
    }

    public void setPerformances(List<PerformancePerson> performances) {
        this.performances = performances;
    }

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

    public void setRoles(List<PersonType> roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
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

    /*public List<PerformancePerson> getPerformances() {
        return performances;
    }

    public void addPerformanceRole(PerformancePerson role) {
        performances.add(role);
    }

    public void removePerformanceRole(PerformancePerson role) {
        performances.remove(role);
    }*/
}

