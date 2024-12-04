package org.example.effectivetask;

import org.example.effectivetask.model.entity.Priority;
import org.example.effectivetask.model.entity.Role;
import org.example.effectivetask.model.entity.Status;
import org.example.effectivetask.model.enums.TaskPriority;
import org.example.effectivetask.model.enums.TaskStatus;
import org.example.effectivetask.model.enums.UserRole;
import org.example.effectivetask.repository.PriorityRepository;
import org.example.effectivetask.repository.RoleRepository;
import org.example.effectivetask.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@EnableCaching
public class EffectiveTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(EffectiveTaskApplication.class, args);
    }
}
