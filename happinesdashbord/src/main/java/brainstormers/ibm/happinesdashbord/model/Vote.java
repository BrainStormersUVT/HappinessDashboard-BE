package brainstormers.ibm.happinesdashbord.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "Vote", uniqueConstraints = {
        @UniqueConstraint(name = "uc_vote_cookie", columnNames = {"user_id", "id"})
})
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Short grade;

    private String comment;

    @Column(nullable = false)
    private Date datetime;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Poll poll;

    public Vote() {

    }

    public Vote(Short grade, String comment, Date datetime, User user, Poll poll) {
        this.grade = grade;
        this.comment = comment;
        this.datetime = datetime;
        this.user = user;
        this.poll = poll;
    }

   public Long getPoll() {
       return poll.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDatetime() {
        Locale locale=new Locale("en", "GB");
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        return dateFormat.format(datetime);
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getUser() { return user.getName(); }

    public void setUser(User user) { this.user = user; }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "grade=" + grade +
                ", comment='" + comment + '\'' +
                ", datetime=" + datetime +
                ", poll=" + poll +
                '}';
    }
}
