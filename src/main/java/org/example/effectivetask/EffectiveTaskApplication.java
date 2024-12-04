package org.example.effectivetask;

import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
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
