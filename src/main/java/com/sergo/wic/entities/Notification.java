package com.sergo.wic.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "notifications")
public class Notification {

    public Notification(String message, User user){
        this.message = message;
        this.user = user;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Notification(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id" )
    private User user;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "is_read")
    private Boolean isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    @Override public int hashCode() {
        return Objects.hash(id, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id &
                message == that.getMessage();
    }
}
