package org.example.effectivetask.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.effectivetask.model.entity.base.BaseEntity;
import org.example.effectivetask.model.enums.TaskPriority;
import org.example.effectivetask.model.enums.TaskStatus;

@Entity
@Table(name = "priorities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Priority extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
