package org.example.effectivetask.repository;

import org.example.effectivetask.model.entity.Role;
import org.example.effectivetask.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(UserRole role);
}
