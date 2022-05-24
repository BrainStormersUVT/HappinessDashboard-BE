package brainstormers.ibm.happinesdashbord.model;

import brainstormers.ibm.happinesdashbord.repository.UserRepository;
import brainstormers.ibm.happinesdashbord.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Poll", uniqueConstraints = {
        @UniqueConstraint(name = "uc_poll_title", columnNames = {"title"})
})
public class Poll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User creator;

    public Poll() {

    }

    public Poll(String title, String description, Date date, User creator) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", creator=" + creator +
                '}';
    }
}
