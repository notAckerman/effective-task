package org.example.effectivetask.repository;

import org.example.effectivetask.model.entity.Status;
import org.example.effectivetask.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByStatus(TaskStatus status);
}
