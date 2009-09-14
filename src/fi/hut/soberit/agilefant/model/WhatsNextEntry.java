package fi.hut.soberit.agilefant.model;

/**
 * @author ahaapala
 */

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

import flexjson.JSON;


@Entity
@Table(
        name = "whatsnextentry",
        uniqueConstraints={@UniqueConstraint(columnNames={"task_id", "user_id"})}
)
public class WhatsNextEntry implements Rankable {
    private int id;
    private int rank = 0;
    private User user;
    private Task task;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne()
    @JSON(include = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @ManyToOne
    @JSON(include = false)
    public Task getTask() {
        return task;
    }
    
    public void setTask(Task task) {
        this.task = task;
    }

    @Column(nullable = false, columnDefinition = "int default 0")
    public int getRank() {
        return rank ;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}