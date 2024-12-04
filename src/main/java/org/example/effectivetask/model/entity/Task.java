package org.example.effectivetask.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.effectivetask.model.entity.base.BaseEntity;

import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Task extends BaseEntity {
    @ManyToOne
    private Priority priority;

    @ManyToOne
    private Status status;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private User author;

    @ManyToOne
    private User assignee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task", orphanRemoval = true)
    private List<Comment> comments;

    public void addComment(Comment comment) {
        comment.setTask(this);
        comments.add(comment);
    }
}
