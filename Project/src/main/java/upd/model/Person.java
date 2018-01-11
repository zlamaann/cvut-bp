package upd.model;

import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PERSON")
@NamedQueries(
        {@NamedQuery(name = "Person.findByUsername", query = "SELECT p FROM Person p WHERE p.username=:username")})
public class Person extends AbstractEntity implements Serializable {


        public Person(PersonType role, String name, String surname, String username, String password, String email) {
            this.role = role;
            this.name = name;
            this.surname = surname;
            this.username = username;
            this.password = password;
            this.email = email;
        }

        @Enumerated(EnumType.STRING)
        private PersonType role;

        @Column(nullable = false, name = "NAME")
        private String name;

        @Column(nullable = false, name = "SURNAME")
        private String surname;

        @Column(nullable = false, name = "USERNAME")
        private String username;

        @Column(nullable = false, name = "PASSWORD")
        private String password;

        @Column(nullable = false, name = "EMAIL")
        private String email;

        public PersonType getRole() {
            return role;
        }

        public void setRole(PersonType role) {
            this.role = role;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

    }

