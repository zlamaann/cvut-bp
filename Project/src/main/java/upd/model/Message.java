package upd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID_MESSAGE")
    private Integer id;

    @Column(nullable = false, name = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @Column(nullable = true, name = "SUBJECT")
    private String subject;

    @Column(nullable = false, name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ID_PERSON")
    private Person author;

    public Message() {
    }

    public Message(Date date, String subject, String description) {
        this.date = date;
        this.subject = subject;
        this.description = description;
    }

    public Message(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}
