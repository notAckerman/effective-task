package org.example.effectivetask.service.status;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.entity.Status;
import org.example.effectivetask.model.enums.TaskStatus;
import org.example.effectivetask.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultStatusService implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public Status getStatus(TaskStatus status) {
        return statusRepository.findByStatus(status).orElseThrow(() -> new RuntimeException("No status found"));
    }

    @Override
    public Status getStatus(Long id) {
        return statusRepository.findById(id).orElseThrow(() -> new RuntimeException("No status found"));
    }
}
