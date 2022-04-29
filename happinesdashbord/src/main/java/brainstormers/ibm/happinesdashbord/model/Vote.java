package brainstormers.ibm.happinesdashbord.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Vote", uniqueConstraints = {
        @UniqueConstraint(name = "uc_vote_cookie", columnNames = {"cookie", "id"})
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

    @Column(nullable = false)
    private Long cookie;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    public Vote() {

    }

    public Vote(Short grade, String comment, Date datetime, Long cookie, Poll poll) {
        this.grade = grade;
        this.comment = comment;
        this.datetime = datetime;
        this.cookie = cookie;
        this.poll = poll;
    }

    public Poll getPoll() {
        return poll;
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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getCookie() {
        return cookie;
    }

    public void setCookie(Long cookie) {
        this.cookie = cookie;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "grade=" + grade +
                ", comment='" + comment + '\'' +
                ", datetime=" + datetime +
                ", cookie=" + cookie +
                ", poll=" + poll +
                '}';
    }
}
