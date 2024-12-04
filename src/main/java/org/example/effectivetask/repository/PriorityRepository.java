package org.example.effectivetask.repository;

import org.example.effectivetask.model.entity.Priority;
import org.example.effectivetask.model.enums.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Optional<Priority> findByPriority(TaskPriority priority);
}
