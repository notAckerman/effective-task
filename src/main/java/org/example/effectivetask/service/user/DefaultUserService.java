package org.example.effectivetask.service.user;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.repository.UserRepository;
import org.example.effectivetask.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.findByName(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
