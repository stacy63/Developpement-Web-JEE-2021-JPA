package com.example.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    private String content;
    @Column(name="creation_date")
    @CreationTimestamp
    private Date creationDate;
    @Column(name="end_date")
    private Date endDate;

    public Task() {
        this.category = new Category();
    }

    public Task(Category category, String content ) {
        this.category = category;
        this.content  = content;
        this.creationDate = new Date();
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, content='%s', category='%s']", id, content, category);
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public String getContent() {
        return content;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCreationDate(Date crtD) {
        this.creationDate = crtD;
    }

    public void setEndDate(Date endD) {
        this.endDate= endD;
    }
    public void setContent(String cont) {
        content = cont;
    }
}

